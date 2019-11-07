package com.example.moviedb.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.Consts
import com.example.moviedb.EventHandlers
import com.example.moviedb.data_models.MainResultsDataModel
import com.example.moviedb.databinding.ItemMainRecyclerviewBinding
import com.squareup.picasso.Picasso

class MovieViewAdapter(private val activity: Activity) : RecyclerView.Adapter<MovieViewAdapter.ViewHolder>() {


    private val TAG: String = javaClass.simpleName

    private var list: List<MainResultsDataModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMainRecyclerviewBinding.inflate(LayoutInflater.from(parent.context))
        binding.executePendingBindings()
        binding.listener = EventHandlers(activity)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])


    fun updateList(newList: List<MainResultsDataModel>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMainRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainResultsDataModel) {
            binding.movieResultModel = item

        }
    }

}