package com.example.newsdemo.data.repository.datasource

import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 8/12/2022
 */

interface NewsLocalDataSource {
   suspend fun saveArticleToDB(article: Article)
   fun getSavedArticles():Flow<List<Article>>
}