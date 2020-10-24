package com.georgcantor.hilt.util

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ApiResponse<T>()
    data class Error<out T : Any>(val errorMsg: Int, val data: T?) : ApiResponse<T>()
    object InProgress : ApiResponse<Nothing>()
}

inline fun <T : Any> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) data?.let { action(it) }

    return this
}

inline fun <T : Any> ApiResponse<T>.onError(action: (Int, T?) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Error) action(this.errorMsg, this.data)

    return this
}

inline fun <T : Any> ApiResponse<T>.onProgress(action: () -> Unit): ApiResponse<T> {
    if (this is ApiResponse.InProgress) action()

    return this
}
