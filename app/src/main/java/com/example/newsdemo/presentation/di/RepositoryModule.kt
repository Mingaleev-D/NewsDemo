package com.example.newsdemo.presentation.di

import com.example.newsdemo.data.repository.NewsRepositoryImpl
import com.example.newsdemo.data.repository.datasource.NewsLocalDataSource
import com.example.newsdemo.data.repository.datasource.NewsRemoteDataSource
import com.example.newsdemo.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

   @Provides
   @Singleton
   fun provideNewsRepository(
      newsRemoteDataSource: NewsRemoteDataSource,
      newsLocalDataSource: NewsLocalDataSource
   ):NewsRepository{
      return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
   }
}