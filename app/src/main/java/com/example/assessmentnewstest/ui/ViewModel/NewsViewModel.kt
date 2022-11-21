package com.example.assessmentnewstest.ui.ViewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assessmentnewstest.NewsApplication
import com.example.assessmentnewstest.models.NewsResponse
import com.example.assessmentnewstest.models.NewsResponseItem
import com.example.assessmentnewstest.repository.NewsRepository
import com.example.assessmentnewstest.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
    app : Application,
    val newsRepository: NewsRepository) : AndroidViewModel(app) {

    val allTypesNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsPage = 1
    var newsResponse : NewsResponse? = null
    init {
        getAllTypesNews("news")
    }

    fun getAllTypesNews(lineupSlug : String) = viewModelScope.launch {
        safeNewsCall(lineupSlug)
    }

    private suspend fun safeNewsCall(lineupSlug: String){
        try {
            if (hasInternetConnection()){
                val response = newsRepository.getAllNews(lineupSlug, newsPage)
                allTypesNews.postValue(handleNewsResponse(response))
            }else{
                allTypesNews.postValue(Resource.Error("No internet connection"))
            }
        }catch (t: Throwable){
            when(t){
                is IOException -> allTypesNews.postValue(Resource.Error("Network Failure"))
                else -> allTypesNews.postValue(Resource.Error("Conversion Error ${t.message}"))
            }
        }
    }

    private fun hasInternetConnection() : Boolean{
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    NetworkCapabilities.TRANSPORT_WIFI -> true
                    NetworkCapabilities.TRANSPORT_CELLULAR -> true
                    NetworkCapabilities.TRANSPORT_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
    private fun handleNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                newsPage++

                if(newsResponse ==  null){
                    newsResponse = resultResponse
                }else{
                    val oldNews = newsResponse
                    val newNews = resultResponse
                    oldNews?.addAll(newNews)
                }
                return Resource.Success(newsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveNews(news : NewsResponseItem) = viewModelScope.launch {
        newsRepository.upsert(news)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

}