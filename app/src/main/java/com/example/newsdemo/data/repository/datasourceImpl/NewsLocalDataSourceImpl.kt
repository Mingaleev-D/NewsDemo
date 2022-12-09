package com.example.newsdemo.data.repository.datasourceImpl

import com.example.newsdemo.data.local.db.ArticleDao
import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import com.example.newsdemo.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 8/12/2022
 */

class NewsLocalDataSourceImpl(
   private val articleDao: ArticleDao
) :NewsLocalDataSource {
   override suspend fun saveArticleToDB(article: Article) {
      articleDao.insert(article)
   }

   override fun getSavedArticles(): Flow<List<Article>> {
      return articleDao.getAllArticles()
   }
}