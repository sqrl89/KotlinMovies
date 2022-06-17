package com.alex.kotlinmovies.view.toprated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.databinding.FragmentMoviesBinding
import com.alex.kotlinmovies.databinding.FragmentTopMoviesBinding
import com.alex.kotlinmovies.view.popular.PopularAdapter
import com.alex.kotlinmovies.viewmodel.PopularMoviesFragmentViewModel
import com.alex.kotlinmovies.viewmodel.TopMoviesFragmentViewModel


class TopMoviesFragment : Fragment(), TopAdapter.TopItemClickListener {

    private var mBinding: FragmentTopMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mRcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentTopMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(TopMoviesFragmentViewModel::class.java)
        mRcView = binding.rvTopMovies
        val moviesAdapter = TopAdapter(this)
        mRcView.adapter = moviesAdapter
        viewModel.getTopMoviesRetrofit()
        viewModel.topMovies.observe(viewLifecycleOwner) { list ->
            moviesAdapter.setList(list.body()!!.results)
        }
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putSerializable("id", id)
        MOVIES.navController.navigate(R.id.action_topMoviesFragment_to_movieDetailsActivity, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}