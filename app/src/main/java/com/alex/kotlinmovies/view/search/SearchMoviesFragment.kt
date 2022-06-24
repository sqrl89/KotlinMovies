package com.alex.kotlinmovies.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.databinding.FragmentSearchMoviesBinding
import com.alex.kotlinmovies.view.movie.PagedAdapter
import com.alex.kotlinmovies.view.movie.PagedAdapter.PagedItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMoviesFragment : Fragment(), PagedItemClickListener {

    private val viewModel by viewModels<SearchMoviesFragmentViewModel>()
    private var mBinding: FragmentSearchMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var moviesAdapter: PagedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentSearchMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        queryListener()

    }

    private fun init() {
        moviesAdapter = PagedAdapter(this)
        binding.rvSearchMovies.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
        }

    }

    private fun queryListener() {
        binding.movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewModel.searchList.observe(viewLifecycleOwner) {
            moviesAdapter.submitData(lifecycle, it)
        }
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putSerializable("id", id)
        MOVIES.navController.navigate(R.id.action_searchMoviesFragment_to_movieDetailsActivity,
            bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}