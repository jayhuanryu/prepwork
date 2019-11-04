package com.example.moviedb.http_utils

import com.example.moviedb.Consts
import com.example.moviedb.data_models.MainDataModel
import com.example.moviedb.data_models.ReviewDataModel
import com.example.moviedb.data_models.TrailerDataModel
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDBServicesInterface {

    companion object {


        fun create() : MovieDBServicesInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Consts.BASE_URL)
                .build()

            return retrofit.create(MovieDBServicesInterface::class.java)
        }
    }

    @GET("movie/{option}")
    fun getList(@Path("option") option:String, @Query("api_key") key : String) : Observable<MainDataModel>

    @GET("movie/{id}/reviews")
    fun getReview(@Path("id") id : Int, @Query("api_key") key : String) : Observable<ReviewDataModel>

    @GET("movie/{id}/videos")
    fun getVideos(@Path("id") id : Int, @Query("api_key")key : String) : Observable<TrailerDataModel>

}