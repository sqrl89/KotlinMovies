package com.alex.kotlinmovies.view.toprated

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alex.kotlinmovies.model.Movies
import com.alex.kotlinmovies.model.apis.ApiService
import com.alex.kotlinmovies.model.repository.RetrofitRepository
import com.alex.kotlinmovies.view.popular.PopularMoviesPagingSource
import com.alex.kotlinmovies.view.toprated.TopMoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TopMoviesFragmentViewModel
@Inject
constructor(private val apiService: ApiService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        TopMoviesPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

}

//class TopMoviesFragmentViewModel: ViewModel() {
//
//    private val repository = RetrofitRepository()
//    val topMovies: MutableLiveData<Response<Movies>> = MutableLiveData()
//
//    fun getTopMoviesRetrofit() {
//        viewModelScope.launch {
//            topMovies.value = repository.getTopMovies()
//        }
//    }
//}