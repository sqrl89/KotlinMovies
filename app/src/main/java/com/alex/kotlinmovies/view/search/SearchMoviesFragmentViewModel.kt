package com.alex.kotlinmovies.view.search

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.alex.kotlinmovies.model.apis.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesFragmentViewModel @Inject constructor(private val apiService: ApiService) :
    ViewModel() {

    private val query = MutableLiveData<String>()

    val searchList = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            SearchMoviesPagingSource(query, apiService)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }
}