package com.example.newsdemo.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsdemo.R
import com.example.newsdemo.databinding.ActivityMainBinding
import com.example.newsdemo.presentation.ui.adapter.NewsAdapter
import com.example.newsdemo.presentation.viewmodel.NewsViewModel
import com.example.newsdemo.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   lateinit var viewModel:NewsViewModel
   @Inject
   lateinit var factory:NewsViewModelFactory
   @Inject
   lateinit var newsAdapter:NewsAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val navHostFragment =
         supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
      val navController = navHostFragment.navController
      binding.bottomNavigationView.setupWithNavController(navController)

      viewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]
   }
}