package com.example.architecturesample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturesample.databinding.ActivityMainBinding
import com.example.architecturesample.models.NewsArticleModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val articlesViewModel by viewModels<ArticlesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        //observe data on viewmodel
        articlesViewModel.getData().observe(this) {
            createRecyclerView(articlesViewModel.dataList?.value)
        }
    }

    private fun createRecyclerView(datalist: Response<List<NewsArticleModel>>?) {
        if (datalist?.isSuccessful == true && !datalist.body().isNullOrEmpty()) {
            binding?.recyclerView?.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = datalist.body()?.distinct()?.let { ItemAdapter(it, this@MainActivity) }
            }
            binding?.errorText?.visibility = View.GONE
        } else {
            Log.e("TAG", "createRecyclerView: ${datalist?.raw()}")
            binding?.errorText?.visibility = View.VISIBLE
        }
        binding?.progressBar?.visibility = View.GONE
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}