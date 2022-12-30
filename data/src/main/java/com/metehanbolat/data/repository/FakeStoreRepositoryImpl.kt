package com.metehanbolat.data.repository

import com.metehanbolat.data.di.coroutine.IoDispatcher
import com.metehanbolat.data.di.coroutine.MainDispatcher
import com.metehanbolat.data.source.local.LocalDataSource
import com.metehanbolat.data.source.remote.RemoteDataSource
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductDbModel
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeStoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : FakeStoreRepository {

    override suspend fun getAllProducts(): NetworkResponse<List<ProductItem>> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getAllProducts()
            } catch (e: Exception) {
                NetworkResponse.Error(exception = e)
            }
        }

    override suspend fun getLimitedProducts(limit: String): NetworkResponse<List<ProductItem>> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getLimitedProducts(limit = limit)
            } catch (e: Exception) {
                NetworkResponse.Error(exception = e)
            }
        }

    override suspend fun getProductFromId(id: String): NetworkResponse<ProductItem> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getProductFromId(id = id)
            } catch (e: Exception) {
                NetworkResponse.Error(exception = e)
            }
        }


    override fun readAllData(): Flow<List<ProductDbModel>> = localDataSource.readAllData()

    override suspend fun addProduct(product: ProductDbModel) =
        withContext(ioDispatcher) {
            try {
                localDataSource.addProduct(product = product)
            } catch (e: Exception) {
                println("FakeStoreRepositoryImpl addProduct Function Exception: ${e.localizedMessage}")
            }
        }
}