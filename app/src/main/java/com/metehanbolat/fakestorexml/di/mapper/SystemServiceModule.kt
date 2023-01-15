package com.metehanbolat.fakestorexml.di.mapper

import androidx.lifecycle.LiveData
import com.metehanbolat.fakestorexml.connectivity.NetworkConnectivityLD
import com.metehanbolat.fakestorexml.connectivity.Status
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
abstract class SystemServiceModule {

    @Binds
    @ServiceScoped
    abstract fun bindNetworkConnectivityManager(networkConnectivityLD: NetworkConnectivityLD): LiveData<Status>
}