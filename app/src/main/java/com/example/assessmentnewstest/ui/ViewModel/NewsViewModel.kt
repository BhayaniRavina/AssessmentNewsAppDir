package com.example.assessmentnewstest.ui.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmentnewstest.api.RetrofitInstance
import com.example.assessmentnewstest.models.NewsResponse
import com.example.assessmentnewstest.repository.NewsRepository
import com.example.assessmentnewstest.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    val allTypesNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsPage = 1

    init {
        getAllTypesNews("news")
    }

    fun getAllTypesNews(lineupSlug : String) = viewModelScope.launch {
        allTypesNews.postValue(Resource.Loading())
        //val response = RetrofitInstance.api.getAllNews(lineupSlug,1) //live
        val response = newsRepository.getAllNews(lineupSlug, newsPage)
        allTypesNews.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
        if (response.isSuccessful){
            response.body()?.let { 
                newsResponse ->  return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message())
    }
}