package com.example.newsdemo.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article

/**
 * @author : Mingaleev D
 * @data : 8/12/2022
 */

@Dao
interface ArticleDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(article:Article)
}