package com.example.moviedb

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.view_models.MainPageViewModel
import com.example.moviedb.view_models.ViewModelFactory


class MainActivity : BaseActivity() {
//
//    private lateinit var viewModel: MainPageViewModel
//    private lateinit var viewModelFactory: ViewModelFactory


    private val mainFragment: MainFragment by lazy {
        MainFragment().newInstance()
    }

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        viewModelFactory = ViewModelFactory(applicationContext)
//        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainPageViewModel::class.java]

        Log.d(TAG, "[onCreate] >> savedInstance + ${savedInstanceState?.getBoolean("isRestoring")}")
        if (supportFragmentManager.findFragmentById(R.id.flContent) != null) {
            Log.d(TAG, "[onCreate] >> activity recreating")
        } else {
            initView()
            switchFragment(mainFragment)
            Log.d(TAG, "[onCreate] >> activity initially created")
        }

//        switchFragment(MainFragment().newInstance())
    }


    private fun initView() {

        //
        supportActionBar?.title = getString(R.string.main_page_title)


    }
//
//    // create an action bar button
//    override
//    fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
//        // If you don't have res/menu, just create a directory named "menu" inside res
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//

    private fun switchFragment(fragment: MainFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit()
    }

    override fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        binding.progressCircular.visibility = View.GONE
    }

    override fun showErrorPopup() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Fail to retrieve the data")
            .setPositiveButton("OK", null)
            .create()
            .show()
    }

}
