package com.alex.kotlinmovies.view.favorites

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
import com.alex.kotlinmovies.databinding.FragmentFavoriteMoviesBinding
import com.alex.kotlinmovies.viewmodel.FavoriteMoviesFragmentViewModel

class FavoriteMoviesFragment : Fragment() {

    private var mBinding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mRcView: RecyclerView
    private val mMoviesAdapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(FavoriteMoviesFragmentViewModel::class.java)
        mRcView = binding.rvFavMovies
        mRcView.adapter = mMoviesAdapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) {
            mMoviesAdapter.setList(it.asReversed())
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
            MOVIES.navController.navigate(R.id.action_favorites_to_detailFragment, bundle)
        }
    }
}