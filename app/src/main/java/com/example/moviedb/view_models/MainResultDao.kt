package com.example.moviedb.view_models

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedb.data_models.MainResultsDataModel

@Dao
public interface MainResultDao {

    @Query("SELECT * FROM main_result_table")
    fun getAllList() : LiveData<List<MainResultsDataModel>>


    @Query("SELECT * FROM main_result_table WHERE isLiked")
    fun getFavoriteList() : LiveData<List<MainResultsDataModel>>

    @Query("SELECT * FROM main_result_table WHERE id is :id")
    fun getItem(id : Int) : LiveData<MainResultsDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : MainResultsDataModel)


    @Query("DELETE FROM main_result_table")
    suspend fun deleteAll()


}