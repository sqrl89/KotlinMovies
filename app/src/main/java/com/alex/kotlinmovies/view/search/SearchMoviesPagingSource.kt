package com.alex.kotlinmovies.view.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.FIRST_PAGE
import com.alex.kotlinmovies.model.MovieItemModel
import com.alex.kotlinmovies.model.apis.ApiService
import retrofit2.HttpException
import java.io.IOException

class SearchMoviesPagingSource(
    private val query: String,
    private val apiService: ApiService,
) : PagingSource<Int, MovieItemModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItemModel>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemModel> {

        return try {
            val currentPage = params.key ?: FIRST_PAGE
            val response = apiService.searchMovies(APIKEY, "ru-RU", query, currentPage)
            val data = response.body()?.results ?: emptyList()
            Log.d("TAG", "load: $response")
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == FIRST_PAGE) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}