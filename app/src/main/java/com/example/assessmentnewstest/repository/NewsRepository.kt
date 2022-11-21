package com.example.assessmentnewstest.repository

import com.example.assessmentnewstest.api.RetrofitInstance
import com.example.assessmentnewstest.db.NewsDatabase

class NewsRepository(val db : NewsDatabase) {
    suspend fun getAllNews(lineupSlug : String, pageNumber : Int) =
        RetrofitInstance.api.getAllNews(lineupSlug,pageNumber)
}