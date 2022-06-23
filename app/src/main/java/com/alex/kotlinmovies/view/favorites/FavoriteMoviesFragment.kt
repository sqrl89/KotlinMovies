package com.alex.kotlinmovies.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.view.favorites.FavoriteAdapter.FavItemClickListener
import com.alex.kotlinmovies.databinding.FragmentFavoriteMoviesBinding

class FavoriteMoviesFragment : Fragment(), FavItemClickListener {

    private val viewModel by viewModels<FavoriteMoviesFragmentViewModel>()
    private var mBinding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mRcView: RecyclerView

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
        mRcView = binding.rvFavMovies
        val moviesAdapter = FavoriteAdapter(this)
        mRcView.adapter = moviesAdapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) {
            moviesAdapter.setList(it.asReversed())

        }
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putSerializable("id", id)
        MOVIES.navController.navigate(R.id.action_favorites_to_movieDetailsActivity, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}