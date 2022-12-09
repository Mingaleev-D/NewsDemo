package com.example.newsdemo.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsdemo.R
import com.example.newsdemo.databinding.FragmentSavedBinding
import com.example.newsdemo.presentation.ui.adapter.NewsAdapter
import com.example.newsdemo.presentation.ui.main.MainActivity
import com.example.newsdemo.presentation.viewmodel.NewsViewModel


class SavedFragment : Fragment() {

   private var _binding: FragmentSavedBinding? = null
   private val binding get() = _binding!!

   private lateinit var viewModel:NewsViewModel
   private lateinit var savedAdapter:NewsAdapter

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentSavedBinding.inflate(inflater, container, false)
      val view = binding.root
      return view
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      viewModel = (activity as MainActivity).viewModel
      savedAdapter = (activity as MainActivity).newsAdapter

      initRecyclerView()

      savedAdapter.setOnItemClickListener {
         val bundle = Bundle().apply {
            putSerializable("selected_article", it)
         }
         findNavController().navigate(R.id.action_homeFragment_to_infoFragment,bundle)
      }

      viewModel.getSaved().observe(viewLifecycleOwner){
         savedAdapter.differ.submitList(it)
      }
   }

   private fun initRecyclerView() {
      binding.rvSaved.apply {
         adapter = savedAdapter
         layoutManager = LinearLayoutManager(activity)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }


}