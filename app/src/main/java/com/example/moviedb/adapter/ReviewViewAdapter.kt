package com.example.moviedb.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.EventHandlers
import com.example.moviedb.data_models.ReviewResultDataModel
import com.example.moviedb.databinding.ItemReviewRecyclerviewBinding

class ReviewViewAdapter(private val activity: Activity) : RecyclerView.Adapter<ReviewViewAdapter.ViewHolder>() {

    private var reviewList : List<ReviewResultDataModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewRecyclerviewBinding.inflate(LayoutInflater.from(parent.context))
        binding.executePendingBindings()
        binding.listener = EventHandlers(activity)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = reviewList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(reviewList[position])

    fun updateList(newList : List<ReviewResultDataModel>) {
        reviewList = newList
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemReviewRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : ReviewResultDataModel) {
            binding.reviewDataResultModel = item
        }

    }
}
