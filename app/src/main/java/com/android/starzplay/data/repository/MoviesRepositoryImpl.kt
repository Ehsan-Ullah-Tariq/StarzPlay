package com.android.starzplay.data.repository

import com.android.starzplay.domain.base.BaseRepository
import com.android.starzplay.data.mappers.toDomainMovies
import com.android.starzplay.data.remote.api.ApiService
import com.android.starzplay.domain.models.MoviesDomainResponse
import com.android.starzplay.domain.repository.MoviesRepository
import com.android.starzplay.utils.ResultResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
  private val api: ApiService
) : MoviesRepository, BaseRepository() {
  override fun getMoviesWithSearch(
    query: String,
    includeAdult: Boolean,
    language: String,
    page: Int
  ): Flow<ResultResource<MoviesDomainResponse>> {
    return safeApiCall {
      api.getMoviesWithSearch(
          query = query,
          includeAdult = includeAdult,
          language = language,
          page = page
      ).toDomainMovies()
    }
  }
}