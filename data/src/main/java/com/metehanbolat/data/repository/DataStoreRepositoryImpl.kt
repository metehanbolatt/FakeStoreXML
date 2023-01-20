package com.metehanbolat.data.repository

import com.metehanbolat.data.di.coroutine.IoDispatcher
import com.metehanbolat.data.source.datastore.TimeDataStoreDataSource
import com.metehanbolat.domain.repository.DataStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val timeDataStoreSource: TimeDataStoreDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : DataStoreRepository {

    override suspend fun saveToDataStore(serviceCallTime: String) {
        withContext(ioDispatcher) {
            timeDataStoreSource.saveToDataStore(serviceCallTime = serviceCallTime)
        }
    }

    override val readFromDataStore: Flow<String> = timeDataStoreSource.readFromDataStore

}