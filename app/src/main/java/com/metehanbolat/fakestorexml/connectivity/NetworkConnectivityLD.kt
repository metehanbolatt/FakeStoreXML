package com.metehanbolat.fakestorexml.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class NetworkConnectivityLD @Inject constructor(
    @ApplicationContext context: Context,
) : MutableLiveData<Status>() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun onActive() {
        super.onActive()
        NetworkRequest.Builder()
            .addTransportType(TRANSPORT_WIFI)
            .addTransportType(TRANSPORT_CELLULAR)
            .build().let {
                connectivityManager.registerNetworkCallback(it, networkCallback)
            }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(Status.Available)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(Status.Lost)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(Status.Unavailable)
        }
    }
}

enum class Status {
    Available, Unavailable, Lost
}