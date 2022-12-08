package com.example.newsdemo.domain.usecase

import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import com.example.newsdemo.domain.repository.NewsRepository

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class SaveNewsUseCase(
   private val newsRepository: NewsRepository
) {

   suspend fun execute(article: Article) = newsRepository.saveNews(article)
}