package com.example.newsdemo.presentation.di

import com.example.newsdemo.presentation.ui.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

   @Provides
   @Singleton
   fun provideNewsAdapter(): NewsAdapter {
      return NewsAdapter()
   }
}