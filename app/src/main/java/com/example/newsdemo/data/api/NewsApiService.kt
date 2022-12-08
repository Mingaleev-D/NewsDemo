package com.example.newsdemo.data.api

import com.example.newsdemo.BuildConfig
import com.example.newsdemo.data.modelDto.topHeadlines.TopHeadlinesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

interface NewsApiService {

   @GET("top-headlines")
   suspend fun fetchTopHeadlines(
      @Query("country") country: String,
      @Query("page") pageNumber: Int,
      @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
      ): Response<TopHeadlinesResponseDto>

   @GET("top-headlines")
   suspend fun fetchSearchTopHeadlines(
      @Query("country") country: String,
      @Query("q") searchQuery: String,
      @Query("page") pageNumber: Int,
      @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
      ): Response<TopHeadlinesResponseDto>
}