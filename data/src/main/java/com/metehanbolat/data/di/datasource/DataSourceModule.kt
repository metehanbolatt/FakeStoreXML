package com.metehanbolat.data.di.datasource

import com.metehanbolat.data.source.datastore.TimeDataStoreDataSource
import com.metehanbolat.data.source.datastore.TimeDataStoreDataSourceImpl
import com.metehanbolat.data.source.local.LocalDataSource
import com.metehanbolat.data.source.local.LocalDataSourceImpl
import com.metehanbolat.data.source.remote.RemoteDataSource
import com.metehanbolat.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindDataStoreDataSource(timeDataStoreDataSourceImpl: TimeDataStoreDataSourceImpl): TimeDataStoreDataSource

}