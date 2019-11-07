package com.example.moviedb.view_models

import androidx.lifecycle.LiveData
import com.example.moviedb.data_models.MainResultsDataModel

class MainResultRepository (private val mainResultDao: MainResultDao) {

    suspend fun insert(mainResultsDataModel: MainResultsDataModel) {
        mainResultDao.insert(mainResultsDataModel)
    }

    fun getAllList() : LiveData<List<MainResultsDataModel>> {
        return mainResultDao.getAllList()
    }

    fun getLikedList() : LiveData<List<MainResultsDataModel>> {
        return mainResultDao.getFavoriteList()
    }

    fun getItem(id : Int) : LiveData<MainResultsDataModel> {
        return mainResultDao.getItem(id)
    }

}

