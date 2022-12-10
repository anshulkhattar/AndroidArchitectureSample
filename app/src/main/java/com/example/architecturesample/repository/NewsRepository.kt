package com.example.architecturesample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.architecturesample.api.ApiInterface
import com.example.architecturesample.models.NewsArticleModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val apiService: ApiInterface) {
    fun fetchDataFromAPI(
        coroutineScope: CoroutineScope,
        liveData: MutableLiveData<Response<List<NewsArticleModel>>>?
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.localizedMessage?.let { Log.e("Exception handled:", it) }
        }
        coroutineScope.launch(Dispatchers.IO + exceptionHandler) {
            withContext(Dispatchers.Main) {
                val res = apiService.getNewsAsync()
                if (res.isSuccessful) {
                    liveData?.postValue(Response.success(res.body()?.articles))
                } else {
                    liveData?.postValue(res.errorBody()?.let { Response.error(it, res.raw()) })
                }
            }
        }
    }
}