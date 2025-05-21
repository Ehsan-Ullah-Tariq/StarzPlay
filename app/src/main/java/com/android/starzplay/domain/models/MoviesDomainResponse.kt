package com.android.starzplay.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDomainResponse(
    val movies: List<MovieDomainDetails>?,
) : Parcelable