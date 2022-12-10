package com.example.architecturesample.api

import com.example.architecturesample.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("everything?q=tesla&sortBy=publishedAt&apiKey=36c0996907b448bb9bd06bab1af72e64")
    suspend fun getNewsAsync(): Response<NewsModel>
}