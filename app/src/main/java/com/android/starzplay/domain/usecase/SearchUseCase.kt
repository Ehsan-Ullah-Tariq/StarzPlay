package com.android.starzplay.domain.usecase

import com.android.starzplay.data.remote.dto.MoviesSearchResultResponse
import com.android.starzplay.domain.models.MoviesDomainResponse
import com.android.starzplay.domain.repository.MoviesRepository
import com.android.starzplay.utils.ResultResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(
        query: String,
        includeAdult: Boolean,
        language: String,
        page: Int
    ): Flow<ResultResource<MoviesDomainResponse>> = repository.getMoviesWithSearch(
        query = query,
        includeAdult = includeAdult,
        language = language,
        page = page
    )
}