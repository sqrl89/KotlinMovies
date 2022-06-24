package com.alex.kotlinmovies.view.toprated

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.databinding.FragmentTopMoviesBinding
import com.alex.kotlinmovies.view.movie.PagedAdapter
import com.alex.kotlinmovies.view.movie.PagedAdapter.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopMoviesFragment : Fragment(), PagedItemClickListener {

    private val viewModel by viewModels<TopMoviesFragmentViewModel>()
    private var mBinding: FragmentTopMoviesBinding? = null
    private val binding get() = mBinding!!
    private lateinit var moviesAdapter: PagedAdapter

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
        moviesAdapter = PagedAdapter(this)
        binding.rvTopMovies.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
        }
        lifecycleScope.launch {
            viewModel.topListData.collect {
                moviesAdapter.submitData(it)
            }
        }
//        mRcView.adapter = moviesAdapter
//        viewModel.getTopMoviesRetrofit()
//        viewModel.topMovies.observe(viewLifecycleOwner) { list ->
//            moviesAdapter.setList(list.body()!!.results)
//        }
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