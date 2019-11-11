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
    var presentingList = MutableLiveData<List<MainResultsDataModel>>()


    init {
        val mainResultDao = MainResultRoomDatabase.getDatabase(context).mainResultDao()

        repository = MainResultRepository(mainResultDao)
        favoriteList = repository.getLikedList()
        presentingList = repository.downloadedList

    }

    fun getTopRatedList() {
        repository.getTopRatedList()
    }

    fun getPopularList() {
        repository.getPopularList()
    }

    fun getLikedList() {
        presentingList.postValue(favoriteList.value)
    }

    fun postValue(list : List<MainResultsDataModel>) {
        if (list != presentingList.value) {
            presentingList.postValue(list)
        }
    }



}

