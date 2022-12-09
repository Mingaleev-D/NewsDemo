package com.example.newsdemo.presentation.di

import com.example.newsdemo.domain.repository.NewsRepository
import com.example.newsdemo.domain.usecase.*
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

   @Provides
   @Singleton
   fun provideGetSearchNewsHeadlinesUseCase(newsRepository: NewsRepository):GetSearchNewsUseCase{
      return GetSearchNewsUseCase(newsRepository)
   }

   @Provides
   @Singleton
   fun provideSaveNewsUseCase(newsRepository: NewsRepository): SaveNewsUseCase {
      return SaveNewsUseCase(newsRepository)
   }

   @Provides
   @Singleton
   fun provideGetSavedNewsUseCase(newsRepository: NewsRepository): GeSavedNewsUseCase {
      return GeSavedNewsUseCase(newsRepository)
   }
   @Provides
   @Singleton
   fun provideDeleteSavedNewsUseCase(newsRepository: NewsRepository): DeleteSavedNewsUseCase {
      return DeleteSavedNewsUseCase(newsRepository)
   }

}