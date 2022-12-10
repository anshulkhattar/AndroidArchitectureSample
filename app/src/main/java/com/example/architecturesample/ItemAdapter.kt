package com.example.architecturesample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturesample.databinding.ItemLayoutBinding
import com.example.architecturesample.models.NewsArticleModel

class ItemAdapter(private val mDataList: List<NewsArticleModel>, private val context: Context) :
    RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    inner class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            title.text = mDataList[position].title
            description.text = mDataList[position].description
            Glide.with(context)
                .load(mDataList[position].urlToImage)
                .into(image)
        }
    }
}