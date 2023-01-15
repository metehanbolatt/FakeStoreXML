package com.metehanbolat.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveToDataStore(serviceCallTime: String)
    val readFromDataStore: Flow<String>
}