package com.example.newsdemo.domain.usecase

import com.example.newsdemo.data.modelDto.topHeadlines.TopHeadlinesResponseDto
import com.example.newsdemo.data.util.Resource
import com.example.newsdemo.domain.repository.NewsRepository

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class GetNewsHeadlinesUseCase(
   private val newsRepository: NewsRepository
) {

   suspend fun execute(
      country: String,
      pageNumber: Int
   ): Resource<TopHeadlinesResponseDto> {
      return newsRepository.fetchNewsHeadlines(country, pageNumber)
   }
}