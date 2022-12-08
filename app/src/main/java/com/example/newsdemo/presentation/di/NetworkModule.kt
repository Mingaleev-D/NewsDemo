package com.example.newsdemo.presentation.di

import com.example.newsdemo.BuildConfig
import com.example.newsdemo.data.remote.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

   @Provides
   @Singleton
   fun provideRetrofit(): Retrofit {
      return Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .baseUrl(BuildConfig.BASE_URL)
         .build()
   }

   @Provides
   @Singleton
   fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
      return retrofit.create(NewsApiService::class.java)
   }
}