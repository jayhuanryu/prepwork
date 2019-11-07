package com.example.moviedb.view_models

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.moviedb.BaseActivity
import com.example.moviedb.BuildConfig
import com.example.moviedb.Consts
import com.example.moviedb.http_utils.MovieDBServicesInterface
import com.example.moviedb.data_models.MainResultsDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainPageViewModel(context: Context) : ViewModel() {

    // what about holding movieId here?
    private val TAG = javaClass.simpleName

    private var repository: MainResultRepository
    var favoriteList: LiveData<List<MainResultsDataModel>>
    var downloadedList = MutableLiveData<List<MainResultsDataModel>>()
    private val dbServices by lazy { MovieDBServicesInterface.create() }


    init {
        val mainResultDao = MainResultRoomDatabase.getDatabase(context).mainResultDao()

        repository = MainResultRepository(mainResultDao)
        favoriteList = repository.getLikedList()

        getPopularList()

    }

    private fun getData(option: String) {
        Log.d(TAG, "[getData] >> IN")
//        activity.showProgressBar()
        dbServices.getList(option, BuildConfig.ApiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

            .subscribeBy(
                onNext = {
                    Log.d(TAG, "[onNext >> ] " + it.results.size)
                    if (downloadedList.value != it.results) {
                        downloadedList.postValue(it.results)
                    }
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
        getData("top_rated")
    }

    fun getPopularList() {
        getData("popular")
    }

    fun getLikedList() {
        downloadedList.postValue(favoriteList.value)
    }



}

