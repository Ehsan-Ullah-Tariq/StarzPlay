package com.android.starzplay.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDomainDetails(
    val id: Int? = null,
    val movieCategory: String? = null,
    val name: String? = null,
    val overview: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
) : Parcelable