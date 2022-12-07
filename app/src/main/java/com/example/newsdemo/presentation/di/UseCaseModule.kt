package com.example.newsdemo.presentation.di

import com.example.newsdemo.domain.repository.NewsRepository
import com.example.newsdemo.domain.usecase.GetNewsHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

   @Provides
   @Singleton
   fun provideGetNewsHeadlinesUseCase(newsRepository: NewsRepository):GetNewsHeadlinesUseCase{
      return GetNewsHeadlinesUseCase(newsRepository)
   }

}