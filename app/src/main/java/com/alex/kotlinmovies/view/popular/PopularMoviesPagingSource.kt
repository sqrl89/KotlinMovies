package com.alex.kotlinmovies.view.popular

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.FIRST_PAGE
import com.alex.kotlinmovies.model.MovieItemModel
import com.alex.kotlinmovies.model.apis.ApiService
import retrofit2.HttpException
import java.io.IOException

class PopularMoviesPagingSource (
    private val apiService: ApiService
): PagingSource<Int, MovieItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemModel> {
        return try {
            val currentPage = params.key ?: FIRST_PAGE
            val response = apiService.getPopularMovies(APIKEY, "ru-RU", currentPage)
            val responseData = mutableListOf<MovieItemModel>()
            val data = response.body()?.results ?: emptyList()
            Log.d("RESULTS:", data.toString())
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == FIRST_PAGE) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemModel>): Int? {
        return null
    }
}