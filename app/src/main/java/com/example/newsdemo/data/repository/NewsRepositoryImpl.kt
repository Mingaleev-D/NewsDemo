package com.example.newsdemo.data.repository

import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import com.example.newsdemo.data.remote.modelDto.topHeadlines.TopHeadlinesResponseDto
import com.example.newsdemo.data.repository.datasource.NewsLocalDataSource
import com.example.newsdemo.data.repository.datasource.NewsRemoteDataSource
import com.example.newsdemo.data.util.Resource
import com.example.newsdemo.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class NewsRepositoryImpl(
   private val newsRemoteDataSource: NewsRemoteDataSource,
   private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

   override suspend fun fetchNewsHeadlines(
      country: String,
      pageNumber: Int
   ): Resource<TopHeadlinesResponseDto> {
      return responseToResource(newsRemoteDataSource.fetchTopHeadlines(country, pageNumber))
   }

   override suspend fun fetchSearchNews(
      country: String,
      searchQuery: String,
      pageNumber: Int
   ): Resource<TopHeadlinesResponseDto> {
      return responseToResource(
         newsRemoteDataSource.fetchSearchTopHeadlines(
            country,
            searchQuery,
            pageNumber
         )
      )
   }

   private fun responseToResource(response: Response<TopHeadlinesResponseDto>): Resource<TopHeadlinesResponseDto> {
      if (response.isSuccessful) {
         response.body()?.let { result ->
            return Resource.Success(result)
         }
      }
      return Resource.Error(response.message())
   }

   override suspend fun saveNews(article: Article) {
      newsLocalDataSource.saveArticleToDB(article)
   }

   override suspend fun deleteNews(article: Article) {
      newsLocalDataSource.deleteArticleFromDB(article)
   }

   override fun getSavedNews(): Flow<List<Article>> {
      return newsLocalDataSource.getSavedArticles()
   }
}