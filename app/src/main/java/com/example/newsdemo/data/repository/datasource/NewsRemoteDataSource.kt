package com.example.newsdemo.data.repository.datasource

import com.example.newsdemo.data.modelDto.topHeadlines.TopHeadlinesResponseDto
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

interface NewsRemoteDataSource {
   suspend fun fetchTopHeadlines(
      country:String,
      pageNumber:Int
   ):Response<TopHeadlinesResponseDto>

   suspend fun fetchSearchTopHeadlines(
      country:String,
      searchQuery:String,
      pageNumber:Int
   ):Response<TopHeadlinesResponseDto>
}