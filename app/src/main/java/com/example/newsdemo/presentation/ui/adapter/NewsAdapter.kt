package com.example.newsdemo.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsdemo.data.remote.modelDto.topHeadlines.Article
import com.example.newsdemo.databinding.ItemNewsHomeBinding

/**
 * @author : Mingaleev D
 * @data : 7/12/2022
 */

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

   inner class MyViewHolder(val binding: ItemNewsHomeBinding) :
      RecyclerView.ViewHolder(binding.root) {
      fun bind(article: Article) {
         binding.apply {
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvSource.text = article.source.name
            tvPublishedAt.text = article.publishedAt
            Glide.with(ivArticleImage.context)
               .load(article.urlToImage)
               .into(ivArticleImage)

            root.setOnClickListener {
             onItemClickListener?.let { it(article) }
            }
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = ItemNewsHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return MyViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val article = differ.currentList[position]
      holder.bind(article)
   }

   override fun getItemCount(): Int {
      return differ.currentList.size
   }

   private val callback = object : DiffUtil.ItemCallback<Article>() {
      override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
         return oldItem.url == newItem.url
      }

      override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
         return oldItem == newItem
      }
   }
   val differ = AsyncListDiffer(this, callback)

   private var onItemClickListener: ((Article) -> Unit)? = null
   fun setOnItemClickListener(listener:(Article) -> Unit) {
      onItemClickListener = listener
   }
}