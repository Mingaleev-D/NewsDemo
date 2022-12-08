package com.example.newsdemo.data.remote.modelDto.topHeadlines


import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponseDto(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)