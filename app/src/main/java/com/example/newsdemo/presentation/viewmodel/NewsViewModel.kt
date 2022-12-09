package com.example.newsdemo.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import com.example.newsdemo.data.remote.modelDto.topHeadlines.TopHeadlinesResponseDto
import com.example.newsdemo.data.util.Resource
import com.example.newsdemo.domain.usecase.GeSavedNewsUseCase
import com.example.newsdemo.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsdemo.domain.usecase.GetSearchNewsUseCase
import com.example.newsdemo.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class NewsViewModel(
   private val app: Application,
   private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
   private val getSearchNewsUseCase: GetSearchNewsUseCase,
   private val saveNewsUseCase: SaveNewsUseCase,
   private val getSavedNewsUseCase: GeSavedNewsUseCase
) : AndroidViewModel(app) {

   val newsHeadlines: MutableLiveData<Resource<TopHeadlinesResponseDto>> = MutableLiveData()

   fun getNewsHeadlines(country: String, pageNumber: Int) = viewModelScope.launch(Dispatchers.IO) {
      newsHeadlines.postValue(Resource.Loading())
      try {
         if (isNetworkAvailable(app)) {
            val apiResult = getNewsHeadlinesUseCase.execute(country, pageNumber)
            newsHeadlines.postValue(apiResult)
         } else {
            newsHeadlines.postValue(Resource.Error("Internet is not available"))
         }
      } catch (e: Exception) {
         newsHeadlines.postValue(Resource.Error(e.message.toString()))
      }


   }

   private fun isNetworkAvailable(context: Context?): Boolean {
      if (context == null) return false
      val connectivityManager =
         context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
         val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
         if (capabilities != null) {
            when {
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                  return true
               }
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)     -> {
                  return true
               }
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                  return true
               }
            }
         }
      } else {
         val activeNetworkInfo = connectivityManager.activeNetworkInfo
         if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
         }
      }
      return false
   }

   //search
   val searchedNews: MutableLiveData<Resource<TopHeadlinesResponseDto>> = MutableLiveData()

   fun seachNews(
      country: String,
      searchQuery: String,
      pageNumber: Int
   ) = viewModelScope.launch {
      searchedNews.postValue(Resource.Loading())
      try {
         if (isNetworkAvailable(app)) {
            val response = getSearchNewsUseCase.execute(country, searchQuery, pageNumber)
            searchedNews.postValue(response)
         } else {
            searchedNews.postValue(Resource.Error("No Internet connection"))
         }
      }catch(e:Exception){
         searchedNews.postValue(Resource.Error(e.message.toString()))
      }

   }

   //local data
   fun saveArticle(article: Article) = viewModelScope.launch {
      saveNewsUseCase.execute(article)
   }
   fun getSaved() = liveData{
      getSavedNewsUseCase.execute().collect{
         emit(it)
      }
   }
}