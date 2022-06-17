package com.alex.kotlinmovies.view.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.data.MovieItemModel
import com.alex.kotlinmovies.databinding.FragmentMoviesBinding
import com.alex.kotlinmovies.viewmodel.PopularMoviesFragmentViewModel

class PopularMoviesFragment : Fragment(){

    private var mBinding: FragmentMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mRcView: RecyclerView
    private val mMoviesAdapter by lazy { PopularAdapter() }

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
        val viewModel = ViewModelProvider(this).get(PopularMoviesFragmentViewModel::class.java)
        viewModel.initDatabase()
        mRcView = binding.rvMovies
        mRcView.adapter = mMoviesAdapter
        viewModel.getPopularMoviesRetrofit()
        viewModel.movies.observe(viewLifecycleOwner) { list ->
            mMoviesAdapter.setList(list.body()!!.results)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


    companion object {
        fun clickMovie(id: MovieItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("id", id)
            MOVIES.navController.navigate(R.id.action_popular_to_detailFragment, bundle)
        }
    }
}