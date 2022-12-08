package com.example.newsdemo.domain.usecase

import com.example.newsdemo.data.remote.modelDto.topHeadlines.TopHeadlinesResponseDto
import com.example.newsdemo.data.util.Resource
import com.example.newsdemo.domain.repository.NewsRepository

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class GetSearchNewsUseCase(
   private val newsRepository: NewsRepository
) {

   suspend fun execute(
      country: String,
      searchQuery: String,
      pageNumber: Int
   ): Resource<TopHeadlinesResponseDto> {
      return newsRepository.fetchSearchNews(country, searchQuery, pageNumber)
   }
}