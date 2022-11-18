package com.example.assessmentnewstest.api

import com.example.assessmentnewstest.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("aggregate_api/v1/items")
    suspend fun getAllNews(
        @Query("lineupSlug")
        lineupSlug : String
    ) : Response<NewsResponse>
}