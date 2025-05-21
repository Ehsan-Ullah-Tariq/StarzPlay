package com.android.starzplay.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchParams(
    val query: String = "",
    val includeAdult: Boolean = false,
    val language: String = "en",
    val page: Int = 1
): Parcelable