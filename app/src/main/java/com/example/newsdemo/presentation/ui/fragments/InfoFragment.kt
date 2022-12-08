package com.example.newsdemo.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsdemo.R
import com.example.newsdemo.databinding.FragmentInfoBinding
import com.example.newsdemo.presentation.ui.main.MainActivity
import com.example.newsdemo.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {

   private var _binding: FragmentInfoBinding? = null
   private val binding get() = _binding!!

   val args:InfoFragmentArgs by navArgs()
   private lateinit var viewModel:NewsViewModel

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentInfoBinding.inflate(inflater, container, false)
      val view = binding.root
      return view
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      viewModel = (activity as MainActivity).viewModel
      val article = args.selectedArticle
      binding.webInfo.apply {
         webViewClient = WebViewClient()
         if(article.url != ""){
            article.url?.let { loadUrl(it) }
         }
      }

      binding.floatingActionButton.setOnClickListener {
         viewModel.saveArticle(article = article)
         Snackbar.make(view,getString(R.string.paged_saved),Snackbar.LENGTH_LONG).show()
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }


}