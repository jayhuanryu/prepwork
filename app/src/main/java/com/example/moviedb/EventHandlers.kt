package com.example.moviedb

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.CheckBox
import com.example.moviedb.data_models.MainResultsDataModel
import com.example.moviedb.data_models.ReviewResultDataModel
import com.example.moviedb.data_models.TrailerResultDataModel
import com.example.moviedb.view_models.DetailPageViewModel

class EventHandlers(val context: Context)  {

    fun onClickPoster(item : MainResultsDataModel) {

        val intent = Intent(context, DetailPageActivity::class.java).apply {
            putExtra("item", item)
        }

        context.startActivity(intent)
    }

    fun onClickTrailer(item : TrailerResultDataModel) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Consts.BASE_YOUTUBE_URL+ item.id))
        context.startActivity(webIntent)
    }


    fun onClickReview(item : ReviewResultDataModel) {

    }



    fun onIsLikedClicked(item : MainResultsDataModel, viewModel : DetailPageViewModel) {
        item.isLiked = !item.isLiked
        viewModel.insert(item)
    }

}