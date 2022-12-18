package com.metehanbolat.fakestorexml.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _networkConnectivity = MutableLiveData(false)
    val networkConnectivity: LiveData<Boolean> = _networkConnectivity

    fun setNetworkConnectivity(networkConnectivity: Boolean) {
        _networkConnectivity.postValue(networkConnectivity)
    }

}