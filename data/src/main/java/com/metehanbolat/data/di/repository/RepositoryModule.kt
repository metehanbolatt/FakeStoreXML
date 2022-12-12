package com.metehanbolat.data.di.repository

import com.metehanbolat.data.repository.FakeStoreRepositoryImpl
import com.metehanbolat.domain.repository.FakeStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindFakeStoreRepository(fakeStoreRepositoryImpl: FakeStoreRepositoryImpl): FakeStoreRepository
}