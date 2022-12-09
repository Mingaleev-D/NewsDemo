package com.example.newsdemo.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsdemo.domain.usecase.*

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class NewsViewModelFactory(
   private val app: Application,
   private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
   private val getSearchNewsUseCase: GetSearchNewsUseCase,
   private val saveNewsUseCase: SaveNewsUseCase,
   private val getSavedNewsUseCase: GeSavedNewsUseCase,
   private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModelProvider.Factory {
   @Suppress("UNCHECKED_CAST")
   override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return NewsViewModel(
         app,
         getNewsHeadlinesUseCase,
         getSearchNewsUseCase,
         saveNewsUseCase,
         getSavedNewsUseCase,
         deleteSavedNewsUseCase
      ) as T
   }
}