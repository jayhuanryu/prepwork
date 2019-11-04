package com.example.moviedb.view_models

import android.database.Observable
import androidx.lifecycle.LiveData
import com.example.moviedb.data_models.MainResultsDataModel

class MainResultRepository (private val mainResultDao: MainResultDao) {


//    var favoriteList : LiveData<List<MainResultsDataModel>> = mainResultDao.getFavoriteList()

    suspend fun insert(mainResultsDataModel: MainResultsDataModel) {
        mainResultDao.insert(mainResultsDataModel)
    }

    suspend fun deleteAll() {
        mainResultDao.deleteAll()
    }

    fun getLikedList() : LiveData<List<MainResultsDataModel>> {
        return mainResultDao.getFavoriteList()
    }

    fun getItem(id : Int) : LiveData<MainResultsDataModel> {
        return mainResultDao.getItem(id)
    }

}

