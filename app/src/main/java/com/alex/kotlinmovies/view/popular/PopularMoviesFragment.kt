package com.alex.kotlinmovies.view.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.databinding.FragmentMoviesBinding
import com.alex.kotlinmovies.view.popular.PopularAdapter.ItemClickListener
import com.alex.kotlinmovies.viewmodel.PopularMoviesFragmentViewModel

class PopularMoviesFragment: Fragment(), ItemClickListener {

    private val viewModel by viewModels<PopularMoviesFragmentViewModel>()
    private var mBinding: FragmentMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mRcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.initDatabase()
        mRcView = binding.rvMovies
        val moviesAdapter = PopularAdapter(this)
        mRcView.adapter = moviesAdapter
        viewModel.getPopularMoviesRetrofit()
        viewModel.movies.observe(viewLifecycleOwner) { list ->
            moviesAdapter.setList(list.body()!!.results)
        }
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putSerializable("id", id)
        MOVIES.navController.navigate(R.id.action_popular_to_movieDetailsActivity, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}