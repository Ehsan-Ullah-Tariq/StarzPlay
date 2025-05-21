package com.android.starzplay.domain.repository

import com.android.starzplay.data.remote.dto.MoviesSearchResultResponse
import com.android.starzplay.domain.models.MoviesDomainResponse
import com.android.starzplay.utils.ResultResource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesWithSearch(
        query: String,
        includeAdult: Boolean,
        language: String,
        page: Int,
    ): Flow<ResultResource<MoviesDomainResponse>>
}