package com.example.assessmentnewstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentnewstest.adapters.NewsAdapter
import com.example.assessmentnewstest.adapters.NewsFilterAdapter
import com.example.assessmentnewstest.api.RetrofitInstance
import com.example.assessmentnewstest.databinding.ActivityMainBinding
import com.example.assessmentnewstest.models.FilterCategories
import com.example.assessmentnewstest.models.NewsResponse
import com.example.assessmentnewstest.models.NewsResponseItem
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsFilterAdapter: NewsFilterAdapter
    lateinit var binding: ActivityMainBinding
    private val itemsList = ArrayList<NewsResponseItem>()
    private val fullItems = ArrayList<NewsResponseItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpVerticalRecyclerView()
        setUpHorizontalRecyclerView()

        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = getAllNews("news").let {
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
                                Toast.makeText(this@MainActivity,"Category is ${it.category}", Toast.LENGTH_LONG).show()
                                newsAdapter.filter?.filter(it.category)
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

    private fun getCatrgories(body: NewsResponse?) : List<FilterCategories>{
        val arrayList = ArrayList<FilterCategories>()

        for(items in 0..body?.size!!-1){
            val filterCategoriesItem = FilterCategories(items, body.get(items)?.type ?: "")
            arrayList.add(items, filterCategoriesItem)
        }
        val filterCategoriesItem = FilterCategories(arrayList.size,"all")
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
}