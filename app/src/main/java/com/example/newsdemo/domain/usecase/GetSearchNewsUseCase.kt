package com.example.newsdemo.domain.usecase

import com.example.newsdemo.data.modelDto.topHeadlines.TopHeadlinesResponseDto
import com.example.newsdemo.data.util.Resource
import com.example.newsdemo.domain.repository.NewsRepository

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class GetSearchNewsUseCase(
   private val newsRepository: NewsRepository
) {

   suspend fun execute(searchQuery:String):Resource<TopHeadlinesResponseDto>{
      return newsRepository.fetchSearchNews(searchQuery)
   }
}