package com.example.assessmentnewstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assessmentnewstest.adapters.NewsAdapter
import com.example.assessmentnewstest.api.RetrofitInstance
import com.example.assessmentnewstest.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpRecyclerView()

        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = getAllNews("news").let {
                try{
                    if (it.isSuccessful){
                        Log.e("successful response: ", Gson().toJson(it.body()))
                        GlobalScope.launch(Dispatchers.Main) {
                            newsAdapter.differ.submitList(it.body())
                        }
                    }else{
                        Log.e("failure response: ", Gson().toJson(it.errorBody()))
                    }
                }catch (ex : JSONException){
                    ex.printStackTrace()
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvNewsItems.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }


    suspend fun getAllNews(lineupSlung : String) =
        RetrofitInstance.api.getAllNews(lineupSlung)
}