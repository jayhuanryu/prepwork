package com.example.moviedb.view_models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedb.data_models.MainResultsDataModel

@Database(entities = [MainResultsDataModel::class], version = 3)
public abstract class MainResultRoomDatabase : RoomDatabase() {

    abstract fun mainResultDao() : MainResultDao

    companion object {
        @Volatile
        private var INSTANCE : MainResultRoomDatabase? = null

        fun getDatabase(context: Context) : MainResultRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainResultRoomDatabase::class.java,
                    "main_result_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}