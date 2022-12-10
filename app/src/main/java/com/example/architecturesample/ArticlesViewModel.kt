package com.example.architecturesample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturesample.models.NewsArticleModel
import com.example.architecturesample.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel(){
    var dataList: MutableLiveData<Response<List<NewsArticleModel>>>? = null

    /**
     * Called to fetch data from API if datalist is null
     * @return list of news returned by the API
     */
    fun getData(): LiveData<Response<List<NewsArticleModel>>> {
        if (dataList == null) {
            dataList = MutableLiveData<Response<List<NewsArticleModel>>>()
            newsRepository.fetchDataFromAPI(viewModelScope, dataList)
        }
        return dataList as LiveData<Response<List<NewsArticleModel>>>
    }
}