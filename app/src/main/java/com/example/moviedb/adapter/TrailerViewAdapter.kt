package com.example.moviedb.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.EventHandlers
import com.example.moviedb.R
import com.example.moviedb.data_models.TrailerResultDataModel
import com.example.moviedb.databinding.ItemTrailerRecyclerviewBinding


class TrailerViewAdapter(private val activity : Activity) : RecyclerView.Adapter<TrailerViewAdapter.ViewHolder>() {


    private val TAG = javaClass.simpleName

    private var trailerList : List<TrailerResultDataModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemTrailerRecyclerviewBinding =
            ItemTrailerRecyclerviewBinding.inflate(LayoutInflater.from(parent.context))
        binding.executePendingBindings()
        binding.listener = EventHandlers(activity)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = trailerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(trailerList[position])

    fun updateList(newList : List<TrailerResultDataModel>) {
        trailerList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding : ItemTrailerRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : TrailerResultDataModel) {
            binding.trailerDataModel = item
        }
    }

}