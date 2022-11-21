package com.example.assessmentnewstest.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentnewstest.NetworkConnectivity.ConnectivityLiveData
import com.example.assessmentnewstest.R
import com.example.assessmentnewstest.adapters.NewsAdapter
import com.example.assessmentnewstest.adapters.NewsFilterAdapter
import com.example.assessmentnewstest.databinding.ActivityMainBinding
import com.example.assessmentnewstest.db.NewsDatabase
import com.example.assessmentnewstest.models.FilterCategories
import com.example.assessmentnewstest.models.NewsResponse
import com.example.assessmentnewstest.models.NewsResponseItem
import com.example.assessmentnewstest.repository.NewsRepository
import com.example.assessmentnewstest.ui.ViewModel.NewsViewModel
import com.example.assessmentnewstest.ui.ViewModel.NewsViewModelProviderFactory
import com.example.assessmentnewstest.util.Constants.Companion.All
import com.example.assessmentnewstest.util.Constants.Companion.QUERY_PAGE_SIZE
import com.example.assessmentnewstest.util.Resource
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsFilterAdapter: NewsFilterAdapter
    lateinit var binding: ActivityMainBinding
    private val itemsList = ArrayList<NewsResponseItem>()
    private val fullItems = ArrayList<NewsResponseItem>()
    private lateinit var connectivityLiveData: ConnectivityLiveData
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        /* start : ActionBar color changed */
        val actionBar: ActionBar?
        actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#430355"))
        actionBar?.setBackgroundDrawable(colorDrawable)
        /* end : ActionBar color changed */

        /* start : ViewModel reference */
        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        /* end : ViewModel reference */

        setUpVerticalRecyclerView()
        setUpHorizontalRecyclerView()

        /* start : get current network state */
        connectivityLiveData= ConnectivityLiveData(application)
        connectivityLiveData.observe(this, Observer {isAvailable->
            when(isAvailable)
            {
                true->Snackbar.make(binding.mainLayout, "Internet Available", Snackbar.LENGTH_LONG)
                    .show()
                false-> Snackbar.make(binding.mainLayout, "No Internet Connectivity", Snackbar.LENGTH_INDEFINITE)
                    .show()
            }
        })
        /* end : get current network state */

        /* start : through observer retrive data from api and fetch news in a list and fill filter */
        viewModel.allTypesNews.observe(this, androidx.lifecycle.Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                            newsResponse ->
                        itemsList.clear()
                        fullItems.clear()
                        itemsList.addAll(newsResponse.toList())
                        fullItems.addAll(newsResponse.toList())
                        manageOfflineNews(newsResponse)

                        val categoriesList = getCategories(newsResponse)
                        newsFilterAdapter.differ.submitList(categoriesList)
                        newsFilterAdapter.setOnItemClickListener{
                            newsAdapter.filter.filter(it.category)
                        }
                        Log.e("CategoriesTypes ", Gson().toJson(categoriesList))

                        val totalPages = newsResponse.size / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.newsPage == totalPages

                        if(isLastPage){
                            binding.rvNewsItems.setPadding(0,0,0,0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Snackbar.make(binding.mainLayout, " $message", Snackbar.LENGTH_INDEFINITE)
                                .show()
                        manageOfflineNews(null)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
        /* end : through observer retrive data from api and fetch news in a list and fill filter */

        /* start : through observer retrive data from api and fetch news in a list and fill filter */


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvNewsItems)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    /* start : Pagination */

    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = binding.rvNewsItems.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotAtLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isAtLastItem && isNotLoadingAndNotAtLastPage && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if(shouldPaginate){
                viewModel.getAllTypesNews("news")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }
    /* end : Pagination */

    /* start : Offline Storage */

    private fun manageOfflineNews(newsResponse: NewsResponse? = null) {
        viewModel.getSavedNews().observe(this, androidx.lifecycle.Observer {
            if(it.size > 0){
                if(!isNetworkAvailable()) {
                    viewModel.viewModelScope.launch {
                        (Dispatchers.Main){
                            itemsList.clear()
                            fullItems.clear()
                            itemsList.addAll(it)
                            fullItems.addAll(it)


                            val categoriesList = getCategories(it)
                            newsFilterAdapter.differ.submitList(categoriesList)
                            newsFilterAdapter.setOnItemClickListener{
                                newsAdapter.filter.filter(it.category)
                            }
                            Log.e("CategoriesTypes ", Gson().toJson(categoriesList))

                            val totalPages = it.size / QUERY_PAGE_SIZE + 2
                            isLastPage = viewModel.newsPage == totalPages

                            if(isLastPage){
                                binding.rvNewsItems.setPadding(0,0,0,0)
                            }
                            newsAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }else{
                saveNews(newsResponse!!)
            }
        })
    }

    private fun saveNews(newsResponse: NewsResponse) : Boolean{
        for(items in 0..newsResponse.size-1){
            viewModel.saveNews(newsResponse.get(items))
        }
        return true
    }
    /* start : Offline storage */

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }


    /* start : Create a list if categorized news */
    private fun getCategories(body: List<NewsResponseItem>) : List<FilterCategories>{
        val arrayList = ArrayList<FilterCategories>()

        for(items in 0..body.size-1){
            val filterCategoriesItem =
                body.get(items).type?.let { FilterCategories(items, it) }
            if (filterCategoriesItem != null) {
                arrayList.add(items, filterCategoriesItem)
            }
        }
        val filterCategoriesItem = FilterCategories(arrayList.size,All)
        arrayList.add(arrayList.size,filterCategoriesItem)
        return arrayList.distinctBy { it.category }
    }
    /* start : Create a list if categorized news */

    private fun setUpVerticalRecyclerView() {
        newsAdapter = NewsAdapter(itemsList,fullItems)
        binding.rvNewsItems.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(this@MainActivity.scrollListener)
        }
    }

    private fun setUpHorizontalRecyclerView(){
        newsFilterAdapter = NewsFilterAdapter()
        binding.rvCategoryList.apply {
            adapter = newsFilterAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
        }
    }

    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = this.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    NetworkCapabilities.TRANSPORT_WIFI -> true
                    NetworkCapabilities.TRANSPORT_CELLULAR -> true
                    NetworkCapabilities.TRANSPORT_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}