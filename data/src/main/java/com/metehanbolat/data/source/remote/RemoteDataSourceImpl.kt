package com.metehanbolat.data.source.remote

import com.metehanbolat.data.api.FakeStoreApi
import com.metehanbolat.data.extension.toProductItem
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val fakeStoreApi: FakeStoreApi) :
    RemoteDataSource {

    override suspend fun getAllProducts(): NetworkResponse<List<ProductItem>> =
        try {
            NetworkResponse.Loading
            val response = fakeStoreApi.getAllProducts().products.map { it.toProductItem() }
            NetworkResponse.Success(response)
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

}