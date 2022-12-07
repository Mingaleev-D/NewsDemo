package com.example.newsdemo.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdemo.R
import com.example.newsdemo.data.util.Resource
import com.example.newsdemo.databinding.FragmentHomeBinding
import com.example.newsdemo.presentation.ui.adapter.NewsAdapter
import com.example.newsdemo.presentation.ui.main.MainActivity
import com.example.newsdemo.presentation.viewmodel.NewsViewModel
import com.facebook.shimmer.Shimmer


class HomeFragment : Fragment() {

   private var _binding: FragmentHomeBinding? = null
   private val binding get() = _binding!!

   private lateinit var viewModel: NewsViewModel
   private lateinit var newsAdapter: NewsAdapter
   private lateinit var shrimmerView: Shimmer
   private var country = "ru"
   private var pageNumber = 1
   private var isScrolling = false
   private var isLoading = false
   private var isLastPage = false
   private var pages = 0

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentHomeBinding.inflate(inflater, container, false)
      val view = binding.root
      return view
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      viewModel = (activity as MainActivity).viewModel
      newsAdapter = (activity as MainActivity).newsAdapter
      newsAdapter.setOnItemClickListener {
         val bundle = Bundle().apply {
            putSerializable("selected_article", it)
         }
         findNavController().navigate(R.id.action_homeFragment_to_infoFragment,bundle)
      }

      initRecyclerView()
      viewNewsList()
   }

   private fun viewNewsList() {
      viewModel.getNewsHeadlines(country, pageNumber)
      viewModel.newsHeadlines.observe(viewLifecycleOwner) { response ->
         when (response) {
            is Resource.Loading -> {
               showProgressBar()
            }
            is Resource.Success -> {
               hideProgressBar()
               response.data?.let {
                  newsAdapter.differ.submitList(it.articles)

                  if (it.totalResults % 20 == 0) {
                     pages = it.totalResults / 20

                  } else {
                     pages = it.totalResults / 20 + 1
                  }
                  isLastPage = pageNumber == pages
               }
            }
            is Resource.Error   -> {
               hideProgressBar()
               response.message?.let {
                  Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
               }
            }
         }
      }
   }

   private fun initRecyclerView() {
      // newsAdapter = NewsAdapter()
      binding.rvBreakingNews.adapter = newsAdapter
      binding.rvBreakingNews.addOnScrollListener(this@HomeFragment.onScrollListener)
   }

   private fun showProgressBar() {
      isLoading = true
      binding.shimmerViewContainer.visibility = View.VISIBLE

   }

   private fun hideProgressBar() {
      isLoading = false
      binding.shimmerViewContainer.visibility = View.INVISIBLE

   }

   private val onScrollListener = object : RecyclerView.OnScrollListener() {
      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
         super.onScrollStateChanged(recyclerView, newState)
         if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true
         }
      }

      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
         super.onScrolled(recyclerView, dx, dy)
         val layoutManager = binding.rvBreakingNews.layoutManager as LinearLayoutManager
         val sizeOfTheCurrentList = layoutManager.itemCount
         val visibleItems = layoutManager.childCount
         val topPosition = layoutManager.findFirstVisibleItemPosition()

         val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
         val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
         if (shouldPaginate) {
            pageNumber++
            viewModel.getNewsHeadlines(country, pageNumber)
            isScrolling = false
         }
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

}