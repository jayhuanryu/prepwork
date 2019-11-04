package com.example.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val TAG : String = javaClass.simpleName

    abstract fun dismissProgressBar()
    abstract fun showProgressBar()
    abstract fun showErrorPopup()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBroadCastReceiver()
    }


    fun registerBroadCastReceiver(){

    }

}
