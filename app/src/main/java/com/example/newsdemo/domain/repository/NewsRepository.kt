package com.example.newsdemo.domain.repository

import com.example.newsdemo.data.modelDto.topHeadlines.Article
import com.example.newsdemo.data.modelDto.topHeadlines.TopHeadlinesResponseDto
import com.example.newsdemo.data.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

interface NewsRepository {

   suspend fun fetchNewsHeadlines(
      country:String,
      pageNumber:Int
   ):Resource<TopHeadlinesResponseDto>
   suspend fun fetchSearchNews(country:String,searchQuery:String,pageNumber: Int):Resource<TopHeadlinesResponseDto>
   suspend fun saveNews(article:Article)
   suspend fun deleteNews(article: Article)
   fun getSavedNews():Flow<List<Article>>
}