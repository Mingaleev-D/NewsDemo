package com.example.newsdemo.presentation.di

import com.example.newsdemo.data.local.db.ArticleDao
import com.example.newsdemo.data.repository.datasource.NewsLocalDataSource
import com.example.newsdemo.data.repository.datasourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

   @Provides
   @Singleton
   fun provideLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource {
      return NewsLocalDataSourceImpl(articleDao)
   }
}