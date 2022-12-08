package com.example.newsdemo.domain.usecase

import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import com.example.newsdemo.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class GeSavedNewsUseCase(
   private val newsRepository: NewsRepository
) {

   fun execute():Flow<List<Article>>{
      return newsRepository.getSavedNews()
   }
}