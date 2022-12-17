package com.metehanbolat.data.repository

import com.metehanbolat.data.di.coroutine.IoDispatcher
import com.metehanbolat.data.source.remote.RemoteDataSource
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeStoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : FakeStoreRepository {

    override suspend fun getAllProducts(): NetworkResponse<List<ProductItem>> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getAllProducts()
            } catch (e: Exception) {
                NetworkResponse.Error(e)
            }
        }

    override suspend fun getLimitedProducts(limit: String): NetworkResponse<List<ProductItem>> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getLimitedProducts(limit = limit)
            } catch (e: Exception) {
                NetworkResponse.Error(e)
            }
        }

}