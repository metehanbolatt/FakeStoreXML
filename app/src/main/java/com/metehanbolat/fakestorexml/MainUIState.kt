package com.metehanbolat.fakestorexml

import androidx.annotation.StringRes

sealed class MainUIState {
    object Loading : MainUIState()
    data class Success(val data: List<MainUIData>) : MainUIState()
    data class Error(@StringRes val message: Int) : MainUIState()
}
