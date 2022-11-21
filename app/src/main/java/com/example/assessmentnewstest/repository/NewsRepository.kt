package com.example.assessmentnewstest.repository

import com.example.assessmentnewstest.api.RetrofitInstance
import com.example.assessmentnewstest.db.NewsDatabase
import com.example.assessmentnewstest.models.NewsResponseItem

class NewsRepository(val db : NewsDatabase) {
    suspend fun getAllNews(lineupSlug : String, pageNumber : Int) =
        RetrofitInstance.api.getAllNews(lineupSlug,pageNumber)

    suspend fun upsert(news : NewsResponseItem) = db.getNewsDao().upsert(news)

    fun getSavedNews() = db.getNewsDao().getAllNews()

}