package com.android.starzplay.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.android.starzplay.utils.ResultResource

abstract class BaseRepository {
    protected fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ResultResource<T>> = flow {
        try {
            val result = apiCall()
            emit(ResultResource.Success(result))
        } catch (e: Exception) {
            println(e.message)
            emit(ResultResource.Error(e.message ?: "Unknown Error"))
        }
    }
}
