package com.example.newsdemo.presentation.di

import android.app.Application
import com.example.newsdemo.domain.usecase.*
import com.example.newsdemo.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FactoryModule {

   @Provides
   @Singleton
   fun provideNewsViewModelFactory(
      app: Application,
      getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
      getSearchNewsUseCase: GetSearchNewsUseCase,
      saveNewsUseCase: SaveNewsUseCase,
      getSavedNewsUseCase: GeSavedNewsUseCase,
      deleteSavedNewsUseCase: DeleteSavedNewsUseCase
   ): NewsViewModelFactory {
      return NewsViewModelFactory(
         app,
         getNewsHeadlinesUseCase,
         getSearchNewsUseCase,
         saveNewsUseCase,
         getSavedNewsUseCase,
         deleteSavedNewsUseCase
      )
   }
}