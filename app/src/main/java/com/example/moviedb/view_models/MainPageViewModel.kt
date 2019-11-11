package com.example.moviedb.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedb.data_models.MainResultsDataModel

class MainPageViewModel(context: Context) : ViewModel() {

    // what about holding movieId here?
    private val TAG = javaClass.simpleName

    private var repository: MainResultRepository
    var favoriteList: LiveData<List<MainResultsDataModel>>
    var downloadedList = MutableLiveData<List<MainResultsDataModel>>()


    init {
        val mainResultDao = MainResultRoomDatabase.getDatabase(context).mainResultDao()

        repository = MainResultRepository(mainResultDao)
        favoriteList = repository.getLikedList()
        downloadedList = repository.downloadedList

    }

    fun getTopRatedList() {
        repository.getTopRatedList()
    }

    fun getPopularList() {
        repository.getPopularList()
    }

    fun getLikedList() {
        downloadedList.postValue(favoriteList.value)
    }

    fun postValue(list : List<MainResultsDataModel>) {
        if (list != downloadedList.value) {
            downloadedList.postValue(list)
        }
    }



}

