package com.example.moviedb

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.adapter.ReviewViewAdapter
import com.example.moviedb.adapter.TrailerViewAdapter
import com.example.moviedb.data_models.MainResultsDataModel
import com.example.moviedb.databinding.ActivityDetailPageBinding
import com.example.moviedb.view_models.DetailPageViewModel
import com.example.moviedb.view_models.ViewModelFactory
import com.squareup.picasso.Picasso

class DetailPageActivity : BaseActivity() {


    private lateinit var viewModel: DetailPageViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    private lateinit var trailerAdapter: TrailerViewAdapter
    private lateinit var reviewAdapter: ReviewViewAdapter

    private val item : MainResultsDataModel by lazy {
        intent.getParcelableExtra<MainResultsDataModel>("item")
    }

    private lateinit var binding: ActivityDetailPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_page)
//        val item = intent.getParcelableExtra<MainResultsDataModel>("item")


        viewModelFactory = ViewModelFactory(application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailPageViewModel::class.java)

        viewModel.postData(item)

        binding.viewModel = viewModel
//        binding.item = item
        binding.listener = EventHandlers(this)
        binding.lifecycleOwner = this
        binding.executePendingBindings()


        initView(binding)
        addObserver()
        initData(item)

    }


    private fun initView(binding: ActivityDetailPageBinding) {
        // actionBar
        supportActionBar.apply {
            title = "MovieDetail"
        }

        // ImageView
//        Picasso.get().load(Consts.BASE_IMAGE_URL+"w185/"+item.poster_path).into(binding.ivPoster)

        trailerAdapter = TrailerViewAdapter(this)
        reviewAdapter = ReviewViewAdapter(this)
        // trailer
        binding.rvTrailers.apply {
            layoutManager =
                LinearLayoutManager(this@DetailPageActivity, LinearLayoutManager.VERTICAL, false)
            adapter = trailerAdapter
        }

        // review
        binding.rvReviews.apply {
            layoutManager =
                LinearLayoutManager(this@DetailPageActivity, LinearLayoutManager.VERTICAL, false)
            adapter = reviewAdapter
        }


    }

    private fun addObserver() {
        viewModel.reviewList.observe(this, Observer {
            reviewAdapter.updateList(it)
        })

        viewModel.trailerList.observe(this, Observer {
            trailerAdapter.updateList(it)
        })

        viewModel.favoriteList.observe(this, Observer {

            for (iter in it) {
                if (iter.id == item.id ) {
                    viewModel.postData(iter)
                }
            }

        })


    }

    private fun initData(item : MainResultsDataModel) {
        viewModel.getReviewData(item.id)
        viewModel.getTrailerData(item.id)
    }


    override fun dismissProgressBar() {
//        binding.progressCircular.visibility = View.GONE
    }

    override fun showProgressBar() {
//        binding.progressCircular.visibility = View.VISIBLE
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
