package com.alex.kotlinmovies.view.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alex.kotlinmovies.model.apis.ApiService
import com.alex.kotlinmovies.view.popular.PopularMoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//class PopularMoviesFragmentViewModel(application: Application): AndroidViewModel(application) {

@HiltViewModel
class PopularMoviesFragmentViewModel
@Inject
constructor(private val apiService: ApiService) : ViewModel() {

//    private val repository = RetrofitRepository()
//    val movies: MutableLiveData<Response<Movies>> = MutableLiveData()
//    val context = application
//
//    fun getPopularMoviesRetrofit() {
//        viewModelScope.launch {
//            movies.value = repository.getPopularMovies()
//        }
//    }

//    fun initDatabase(){
//        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
//        REALIZATION = MoviesRepositoryRealization(daoMovie)
//    }

    val listData = Pager(PagingConfig(pageSize = 1)) {
        PopularMoviesPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}