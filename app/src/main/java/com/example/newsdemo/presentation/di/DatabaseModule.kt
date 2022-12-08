package com.example.newsdemo.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.newsdemo.data.local.db.ArticleDao
import com.example.newsdemo.data.local.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

   @Provides
   @Singleton
   fun provideNewsDatabase(app:Application): ArticleDatabase{
      return Room.databaseBuilder(app,ArticleDatabase::class.java,"new_db")
         .fallbackToDestructiveMigration()
         .build()
   }

   @Provides
   @Singleton
   fun provideNewsDao(articleDatabase: ArticleDatabase):ArticleDao{
      return articleDatabase.getArticle()
   }
}