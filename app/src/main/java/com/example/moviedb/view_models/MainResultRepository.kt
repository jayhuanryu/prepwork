package com.example.moviedb.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedb.BuildConfig
import com.example.moviedb.data_models.MainResultsDataModel
import com.example.moviedb.http_utils.MovieDBServicesInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainResultRepository (private val mainResultDao: MainResultDao) {

    private val dbServices by lazy { MovieDBServicesInterface.create() }
    var downloadedList = MutableLiveData<List<MainResultsDataModel>>()
    private val TAG = javaClass.simpleName


    init {
        getPopularList()
    }

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

    private fun getData(option: String, sorted : String) {
        Log.d(TAG, "[getData] >> IN")
//        activity.showProgressBar()
        dbServices.getList(option, BuildConfig.ApiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

            .subscribeBy(
                onNext = {
                    Log.d(TAG, "[onNext >> ] " + it.results.size)
//                    if (downloadedList.value != it.results) {
//                        if (sorted == "top_rated") {
//                            topRatedList.postValue(it.results)
//                        } else {
//                            popularList.postValue(it.results)
//                        }
//                    }
                    downloadedList.postValue(it.results)

                },
                onComplete = {
                    Log.d(TAG, "[onCompleted]")
//                    activity.dismissProgressBar()
                },
                onError = {
                    Log.e(TAG, it.message)
                }
            )
    }


    fun getTopRatedList() {
        getData("top_rated","top_rated")
    }

    fun getPopularList() {
        getData("popular", "popular")
    }


}

