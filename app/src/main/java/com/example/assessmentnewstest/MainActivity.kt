package com.example.assessmentnewstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.assessmentnewstest.api.RetrofitInstance
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }

        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response =  getAllNews("news")
            if (response != null && response.isSuccessful)
                Log.e("successful response: ", Gson().toJson(response.body()))
            else
                Log.e("failure response: ", Gson().toJson(response.errorBody()))
        }
    }


    suspend fun getAllNews(lineupSlung : String) =
        RetrofitInstance.api.getAllNews(lineupSlung)
}