package com.example.newsdemo.presentation.di

import android.app.Application
import com.example.newsdemo.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsdemo.domain.usecase.GetSearchNewsUseCase
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
      app:Application,
      getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
      getSearchNewsUseCase: GetSearchNewsUseCase
   ):NewsViewModelFactory{
      return NewsViewModelFactory(app, getNewsHeadlinesUseCase,getSearchNewsUseCase)
   }
}