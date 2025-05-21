package com.android.starzplay.data.mappers

import com.android.starzplay.data.remote.dto.MovieDetails
import com.android.starzplay.data.remote.dto.MoviesSearchResultResponse
import com.android.starzplay.domain.models.MovieDomainDetails
import com.android.starzplay.domain.models.MoviesDomainResponse

fun MoviesSearchResultResponse.toDomainMovies(): MoviesDomainResponse {
    return MoviesDomainResponse(
        movies = results?.map { it.toMovieDetails() }
    )
}

fun MovieDetails.toMovieDetails(): MovieDomainDetails {
    return MovieDomainDetails(
        id = id,
        movieCategory = mediaType,
        title = title,
        video = video,
        name = name,
        overview = overview
    )
}