package com.example.assessmentnewstest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentnewstest.NetworkConnectivity.ConnectivityLiveData
import com.example.assessmentnewstest.adapters.NewsAdapter
import com.example.assessmentnewstest.adapters.NewsFilterAdapter
import com.example.assessmentnewstest.api.RetrofitInstance
import com.example.assessmentnewstest.databinding.ActivityMainBinding
import com.example.assessmentnewstest.models.FilterCategories
import com.example.assessmentnewstest.models.NewsResponse
import com.example.assessmentnewstest.models.NewsResponseItem
import com.example.assessmentnewstest.util.Constants.Companion.All
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.*
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsFilterAdapter: NewsFilterAdapter
    lateinit var binding: ActivityMainBinding
    private val itemsList = ArrayList<NewsResponseItem>()
    private val fullItems = ArrayList<NewsResponseItem>()
    private lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpVerticalRecyclerView()
        setUpHorizontalRecyclerView()
        checkInternetConnection(isNetworkAvailable)
        getLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            getLiveData()
            newsAdapter.notifyDataSetChanged()
        }
    }

    private fun getLiveData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getAllNews("news").let {
                try{
                    if (it.isSuccessful){
                        Log.e("successful response: ", Gson().toJson(it.body()))
                        GlobalScope.launch(Dispatchers.Main) {
                            it.body()?.let { it1 ->
                                itemsList.addAll(it1)
                                fullItems.addAll(it1)
                            }
                        }
                        val categoriesList = getCatrgories(it.body())
                        GlobalScope.launch(Dispatchers.Main) {
                            newsFilterAdapter.differ.submitList(categoriesList)
                            newsFilterAdapter.setOnItemClickListener{
                                //Toast.makeText(this@MainActivity,"Category is ${it.category}", Toast.LENGTH_LONG).show()
                                newsAdapter.filter.filter(it.category)
                            }
                        }
                        Log.e("CategoriesTypes ", Gson().toJson(categoriesList))
                    }else{
                        Log.e("failure response: ", Gson().toJson(it.errorBody()))
                    }
                }catch (ex : JSONException){
                    ex.printStackTrace()
                }
            }
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

    suspend fun getAllNews(lineupSlung : String) =
        RetrofitInstance.api.getAllNews(lineupSlung)

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