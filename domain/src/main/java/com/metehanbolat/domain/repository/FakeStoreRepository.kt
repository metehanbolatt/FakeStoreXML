package com.metehanbolat.domain.repository

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductDbModel
import com.metehanbolat.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface FakeStoreRepository {

    /** Api */
    suspend fun getAllProducts(): NetworkResponse<List<ProductItem>>
    suspend fun getLimitedProducts(limit: String): NetworkResponse<List<ProductItem>>

    /** Room Database */
    val readAllData: Flow<List<ProductDbModel>>
    suspend fun addProduct(product: ProductDbModel)

}