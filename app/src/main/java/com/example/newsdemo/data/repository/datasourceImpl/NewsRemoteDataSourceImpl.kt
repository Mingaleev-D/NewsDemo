package com.example.newsdemo.data.repository.datasourceImpl

import com.example.newsdemo.data.api.NewsApiService
import com.example.newsdemo.data.modelDto.topHeadlines.TopHeadlinesResponseDto
import com.example.newsdemo.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class NewsRemoteDataSourceImpl(
   private val newsApiService: NewsApiService
) : NewsRemoteDataSource {

   override suspend fun fetchTopHeadlines(
      country: String,
      pageNumber: Int
   ): Response<TopHeadlinesResponseDto> {
      return newsApiService.fetchTopHeadlines(country, pageNumber)
   }

   override suspend fun fetchSearchTopHeadlines(
      country: String,
      searchQuery: String,
      pageNumber: Int
   ): Response<TopHeadlinesResponseDto> {
      return newsApiService.fetchSearchTopHeadlines(country, searchQuery, pageNumber)
   }
}