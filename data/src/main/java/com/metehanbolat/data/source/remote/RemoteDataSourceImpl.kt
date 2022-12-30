package com.metehanbolat.data.source.remote

import com.metehanbolat.data.api.FakeStoreApi
import com.metehanbolat.data.extension.toProductItem
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val fakeStoreApi: FakeStoreApi
) : RemoteDataSource {

    override suspend fun getAllProducts(): NetworkResponse<List<ProductItem>> =
        try {
            val response = fakeStoreApi.getAllProducts().map { it.toProductItem() }
            NetworkResponse.Success(result = response)
        } catch (e: Exception) {
            NetworkResponse.Error(exception = e)
        }

    override suspend fun getLimitedProducts(limit: String): NetworkResponse<List<ProductItem>> =
        try {
            val response = fakeStoreApi.getLimitedProducts(limit = limit).map { it.toProductItem() }
            NetworkResponse.Success(result = response)
        } catch (e: Exception) {
            NetworkResponse.Error(exception = e)
        }

    override suspend fun getProductFromId(id: String): NetworkResponse<ProductItem> =
        try {
            val response = fakeStoreApi.getProductFromId(id = id).toProductItem()
            NetworkResponse.Success(result = response)
        } catch (e: Exception) {
            NetworkResponse.Error(exception = e)
        }

}