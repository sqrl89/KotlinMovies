package com.alex.kotlinmovies.view.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alex.kotlinmovies.model.apis.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopMoviesFragmentViewModel
@Inject
constructor(private val apiService: ApiService) : ViewModel() {

    val topListData = Pager(PagingConfig(pageSize = 6)) {
        TopMoviesPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

}
