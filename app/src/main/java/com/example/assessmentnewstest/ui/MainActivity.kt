package com.example.assessmentnewstest.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentnewstest.NetworkConnectivity.ConnectivityLiveData
import com.example.assessmentnewstest.R
import com.example.assessmentnewstest.adapters.NewsAdapter
import com.example.assessmentnewstest.adapters.NewsFilterAdapter
import com.example.assessmentnewstest.api.RetrofitInstance
import com.example.assessmentnewstest.databinding.ActivityMainBinding
import com.example.assessmentnewstest.db.NewsDatabase
import com.example.assessmentnewstest.models.FilterCategories
import com.example.assessmentnewstest.models.NewsResponse
import com.example.assessmentnewstest.models.NewsResponseItem
import com.example.assessmentnewstest.repository.NewsRepository
import com.example.assessmentnewstest.ui.ViewModel.NewsViewModel
import com.example.assessmentnewstest.ui.ViewModel.NewsViewModelProviderFactory
import com.example.assessmentnewstest.util.Constants.Companion.All
import com.example.assessmentnewstest.util.Resource
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.*
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
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

        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        setUpVerticalRecyclerView()
        setUpHorizontalRecyclerView()
        checkInternetConnection(isNetworkAvailable)

        viewModel.allTypesNews.observe(this, androidx.lifecycle.Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsResponse ->
                        itemsList.addAll(newsResponse)
                        fullItems.addAll(newsResponse)

                        val categoriesList = getCatrgories(newsResponse)
                        newsFilterAdapter.differ.submitList(categoriesList)
                        newsFilterAdapter.setOnItemClickListener{
                            newsAdapter.filter.filter(it.category)
                        }

                        Log.e("CategoriesTypes ", Gson().toJson(categoriesList))
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun checkInternetConnection(isNetworkAvailable: Boolean) {
        if(!isNetworkAvailable){
            Snackbar.make(binding.root, "No Internet", Snackbar.LENGTH_INDEFINITE)
                .show()
        }
        connectivityLiveData= ConnectivityLiveData(application)
        connectivityLiveData.observe(this) { isAvailable ->
            when (isAvailable) {
                true -> Snackbar.make(
                    binding.root,
                    getString(R.string.internet_connected),
                    Snackbar.LENGTH_SHORT
                ).show()
                false -> Snackbar.make(
                    binding.root,
                    getString(R.string.no_internet),
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkInternetConnection(isNetworkAvailable)
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun getCatrgories(body: NewsResponse?) : List<FilterCategories>{
        val arrayList = ArrayList<FilterCategories>()

        for(items in 0..body?.size!!-1){
            val filterCategoriesItem = FilterCategories(items, body.get(items).type)
            arrayList.add(items, filterCategoriesItem)
        }
        val filterCategoriesItem = FilterCategories(arrayList.size,All)
        arrayList.add(arrayList.size,filterCategoriesItem)
        return arrayList.distinctBy { it.category }
    }

    private fun setUpVerticalRecyclerView() {
        newsAdapter = NewsAdapter(itemsList,fullItems)
        binding.rvNewsItems.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setUpHorizontalRecyclerView(){
        newsFilterAdapter = NewsFilterAdapter()
        binding.rvCategoryList.apply {
            adapter = newsFilterAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
        }
    }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = connectivityManager.activeNetwork ?: return false
                val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
                return when {
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                @Suppress("DEPRECATION") val networkInfo =
                    connectivityManager.activeNetworkInfo ?: return false
                @Suppress("DEPRECATION")
                return networkInfo.isConnected
            }
        }
}