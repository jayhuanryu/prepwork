package com.example.moviedb

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
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

    private lateinit var mContext : Context

    private var favoriteList : List<MainResultsDataModel> = mutableListOf()

    private val   viewModel: MainPageViewModel by lazy {
        ViewModelProviders.of(this@MainFragment,viewModelFactory)[MainPageViewModel::class.java]
    }
    private lateinit var viewModelFactory: ViewModelFactory




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.setContentView(activity as Activity, R.layout.fragment_main)

        initView()
        if (savedInstanceState == null || !savedInstanceState?.getBoolean("is")) {
            viewModel.getPopularList()
        }

        setHasOptionsMenu(true)

        return view
    }


    private fun initView() {

        viewAdapter = MovieViewAdapter(activity as Activity)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = viewAdapter
        }


    }

    fun newInstance(): MainFragment {
        return MainFragment()

    }


    fun updateView(id: Int) {
        when (id) {
            R.id.btnSortPopular -> viewModel.getPopularList()
            R.id.btnSortVote -> viewModel.getTopRatedList()
            R.id.btnLiked -> viewModel.getLikedList()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context


        viewModelFactory = ViewModelFactory(mContext)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

      override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       viewModel.favoriteList.observe(this, Observer {
            viewAdapter.updateList(it)
                       favoriteList = it
        })

        viewModel.downloadedList.observe(this, Observer {
            viewAdapter.updateList(it)

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

        when(item.itemId) {
            R.id.btnSortPopular -> viewModel.getPopularList()
            R.id.btnSortVote -> viewModel.getTopRatedList()
            R.id.btnLiked -> viewAdapter.updateList(favoriteList)
//            R.id.btnLiked -> viewModel.getLikedList()
        }

        return super.onOptionsItemSelected(item)
    }



}



