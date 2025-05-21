package com.android.starzplay.data.remote.dto


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class MoviesSearchResultResponse(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<MovieDetails>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
): Parcelable