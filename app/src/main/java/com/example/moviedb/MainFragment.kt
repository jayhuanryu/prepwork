package com.example.moviedb

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.adapter.MovieViewAdapter
import com.example.moviedb.data_models.MainResultsDataModel
import com.example.moviedb.databinding.FragmentMainBinding
import com.example.moviedb.view_models.MainPageViewModel
import com.example.moviedb.view_models.ViewModelFactory
import dalvik.system.BaseDexClassLoader
import java.lang.Exception

class MainFragment : Fragment() {


    private val TAG = javaClass.simpleName

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewAdapter: MovieViewAdapter

    private lateinit var mContext: Context

    private val viewModel: MainPageViewModel by lazy {
        ViewModelProviders.of(this@MainFragment, viewModelFactory)[MainPageViewModel::class.java]
    }
    private lateinit var viewModelFactory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.setContentView(activity as Activity, R.layout.fragment_main)

        initView()
        setHasOptionsMenu(true)
        addObserver()

        return view
    }


    private fun initView() {

        viewAdapter = MovieViewAdapter(activity as Activity)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, getOrientation())
            adapter = viewAdapter
        }



    }

    private fun getOrientation() : Int{
        return if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            2
        else
            4
    }

    fun newInstance(): MainFragment {
        return MainFragment()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isRestoring", true)
        super.onSaveInstanceState(outState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context


        viewModelFactory = ViewModelFactory(mContext)
    }

    private fun addObserver() {

        viewModel.favoriteList.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                Toast.makeText(mContext, "List is empty", Toast.LENGTH_SHORT).show()
            }
            viewAdapter.updateList(it)
        })

        viewModel.downloadedList.observe(this, Observer {
            if (it != null) {
                if (it.isEmpty()) {
                    Toast.makeText(mContext, "List is empty", Toast.LENGTH_SHORT).show()
                }
                viewAdapter.updateList(it)
            }
            else {
                Toast.makeText(mContext, "List is empty", Toast.LENGTH_SHORT).show()
                viewAdapter.updateList(mutableListOf())
            }

        })



    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "[onCreateOptionsMenu] >> IN")
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "[onOptionsItemSelected] >> IN")

        when (item.itemId) {
            R.id.btnSortPopular -> viewModel.getPopularList()
            R.id.btnSortVote -> viewModel.getTopRatedList()
            R.id.btnLiked -> viewModel.getLikedList()
        }

        return super.onOptionsItemSelected(item)
    }


}



