package com.example.newsdemo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article

/**
 * @author : Mingaleev D
 * @data : 8/12/2022
 */

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase() {

   abstract fun getArticle():ArticleDao
}