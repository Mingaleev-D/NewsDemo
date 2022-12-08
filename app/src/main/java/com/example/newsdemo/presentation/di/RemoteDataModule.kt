package com.example.newsdemo.presentation.di

import com.example.newsdemo.data.remote.api.NewsApiService
import com.example.newsdemo.data.repository.datasource.NewsRemoteDataSource
import com.example.newsdemo.data.repository.datasourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

   @Provides
   @Singleton
   fun provideNewsRemoteDataSource(newsApiService: NewsApiService):NewsRemoteDataSource{
      return NewsRemoteDataSourceImpl(newsApiService)
   }
}