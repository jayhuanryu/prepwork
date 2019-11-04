package com.example.moviedb.view_models

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.moviedb.BuildConfig
import com.example.moviedb.Consts
import com.example.moviedb.data_models.MainResultsDataModel
import com.example.moviedb.http_utils.MovieDBServicesInterface
import com.example.moviedb.data_models.ReviewResultDataModel
import com.example.moviedb.data_models.TrailerResultDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailPageViewModel(private val context: Context) : ViewModel() {


    private val TAG = javaClass.simpleName

    var reviewList: MutableLiveData<List<ReviewResultDataModel>> = MutableLiveData()
    var trailerList: MutableLiveData<List<TrailerResultDataModel>> = MutableLiveData()

    var mainResultDataModel : MutableLiveData<MainResultsDataModel> = MutableLiveData()

    var favoriteList: LiveData<List<MainResultsDataModel>>


    private var repository : MainResultRepository
    private val dbServices by lazy { MovieDBServicesInterface.create() }


    init {
        val mainResultDao = MainResultRoomDatabase.getDatabase(context).mainResultDao()
        repository = MainResultRepository(mainResultDao)
        favoriteList = repository.getLikedList()
    }

    fun getReviewData(movieId : Int) {
//        activity.showProgressBar()
        dbServices.getReview(movieId, BuildConfig.ApiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    Log.d(TAG, "[getReviewData] >> onNext")
                    if (!it.result.isNullOrEmpty()) {
                        Log.d(TAG, "[getReviewData] >> onNext : result is empty")
                        reviewList.value = it.result
                    }
                    else {
                        Log.d(TAG, "[getReviewData] >> onNext : result is empty")
                        val temporaryReviewItem = ReviewResultDataModel("","No Review is Added", "", "")
                        val temporaryReviewList = ArrayList<ReviewResultDataModel>()
                        temporaryReviewList.add(temporaryReviewItem)
                        reviewList.value = temporaryReviewList

                    }
                },
                onComplete = {
                    Log.d(TAG, "[getReviewData] >> onComplete")
//                    activity.dismissProgressBar()
                },
                onError = {
                    Log.e(TAG, "[getReviewData] >> onError")
//                    activity.dismissProgressBar()
//                    activity.showErrorPopup()
                }
            )
    }

    fun getTrailerData(movieId : Int) {
//        activity.showProgressBar()
        dbServices.getVideos(movieId, BuildConfig.ApiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    Log.d(TAG, "[getTrailerData] >> onNext")
                    if (!it.results.isNullOrEmpty()) {
                        trailerList.value = it.results
                    }
                    else {
                        Log.d(TAG, "[getReviewData] >> onNext : result is empty")
                        val temporaryTrailerItem = TrailerResultDataModel("","", "","","","",0, "")
                        val temporaryTrailerList = ArrayList<TrailerResultDataModel>()
                        temporaryTrailerList.add(temporaryTrailerItem)
                        trailerList.value = temporaryTrailerList

                    }
                },
                onComplete = {
                    Log.d(TAG, "[getTrailerData] >> onComplete")
//                    activity.dismissProgressBar()
                },
                onError = {
                    Log.e(TAG, "[getTrailerData] >> onError")
//                    activity.dismissProgressBar()
//                    activity.showErrorPopup()
                }
            )
    }

    fun insert() = viewModelScope.launch {
        val item = mainResultDataModel.value!!
        item.isLiked = !item.isLiked
        repository.insert(item)
    }

    fun postData(item : MainResultsDataModel) {
        mainResultDataModel.value = item

    }


}