package com.example.assessmentnewstest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assessmentnewstest.models.NewsResponseItem

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(newsData : NewsResponseItem) : Long

    @Query("SELECT * FROM news")
    fun getAllNews() : LiveData<List<NewsResponseItem>>
}