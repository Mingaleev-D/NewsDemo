package com.example.newsdemo.data.local.db

import androidx.room.*
import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 8/12/2022
 */

@Dao
interface ArticleDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(article:Article)

   @Query("SELECT * FROM articles")
   fun getAllArticles():Flow<List<Article>>

   @Delete
   suspend fun deleteArticle(article: Article)
}