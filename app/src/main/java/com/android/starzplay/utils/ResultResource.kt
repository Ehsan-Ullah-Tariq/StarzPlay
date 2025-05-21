package com.android.starzplay.utils

sealed class ResultResource<out T> {
    object Loading : ResultResource<Nothing>()
    data class Success<out T> (val value: T) : ResultResource<T>()
    data class Error(val message: String?) : ResultResource<Nothing>()
}
