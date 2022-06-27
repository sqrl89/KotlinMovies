package com.alex.kotlinmovies.view.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alex.kotlinmovies.databinding.FragmentMoviesBinding
import com.alex.kotlinmovies.model.MovieItemModel
import com.alex.kotlinmovies.view.movie.PagedAdapter
import com.alex.kotlinmovies.view.movie.PagedAdapter.*
import com.alex.kotlinmovies.view.toprated.TopMoviesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesFragment : Fragment(), PagedItemClickListener {

    private val viewModel by viewModels<PopularMoviesFragmentViewModel>()
    private var mBinding: FragmentMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var moviesAdapter: PagedAdapter

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
        moviesAdapter = PagedAdapter(this)
        binding.rvMovies.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
        }
        lifecycleScope.launch {
            viewModel.popularListData.collect {
                moviesAdapter.submitData(it)
            }
        }
    }

    override fun onItemClick(movieItemModel: MovieItemModel) {
        val action = PopularMoviesFragmentDirections.actionPopularToMovieDetailsActivity(movieItemModel)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}