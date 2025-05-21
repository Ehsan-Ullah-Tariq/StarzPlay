package com.android.starzplay.data.remote.api

import com.android.starzplay.data.remote.dto.MoviesSearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/multi")
    suspend fun getMoviesWithSearch(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesSearchResultResponse
}