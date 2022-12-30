package com.metehanbolat.fakestorexml

import androidx.annotation.StringRes

sealed class MainUIState<out T: Any> {
    object Loading : MainUIState<Nothing>()
    data class Success<T: Any>(val data: T) : MainUIState<T>()
    data class Error(@StringRes val message: Int) : MainUIState<Nothing>()
}
