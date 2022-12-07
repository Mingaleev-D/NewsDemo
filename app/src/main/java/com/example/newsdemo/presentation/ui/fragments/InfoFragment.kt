package com.example.newsdemo.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsdemo.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

   private var _binding: FragmentInfoBinding? = null
   private val binding get() = _binding!!

   val args:InfoFragmentArgs by navArgs()

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
      val article = args.selectedArticle
      binding.webInfo.apply {
         webViewClient = WebViewClient()
         if(article.url != ""){
            loadUrl(article.url)
         }
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }


}