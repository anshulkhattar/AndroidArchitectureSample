package com.example.architecturesample.models

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Long? = null,
    @SerializedName("articles")
    val articles: ArrayList<NewsArticleModel>? = null,
)

data class NewsArticleModel(
    var title: String? = null,
    var description: String? = null,
    var urlToImage: String? = null
)
