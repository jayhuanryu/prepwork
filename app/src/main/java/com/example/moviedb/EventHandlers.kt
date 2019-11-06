package com.example.moviedb

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
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

        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + item.key))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + item.key))
        try {
            context.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(intentBrowser)
        }
    }


    fun onClickReview(item : ReviewResultDataModel) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        context.startActivity(webIntent)
    }



    fun onIsLikedClicked(viewModel : DetailPageViewModel) {
        viewModel.insert()
    }

}