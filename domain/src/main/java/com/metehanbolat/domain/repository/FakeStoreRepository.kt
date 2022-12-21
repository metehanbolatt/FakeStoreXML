package com.metehanbolat.domain.repository

import androidx.lifecycle.LiveData
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductDbModel
import com.metehanbolat.domain.model.ProductItem

interface FakeStoreRepository {

    /** Api */
    suspend fun getAllProducts(): NetworkResponse<List<ProductItem>>
    suspend fun getLimitedProducts(limit: String): NetworkResponse<List<ProductItem>>

    /** Room Database */
    val readAllData: LiveData<List<ProductDbModel>>
    suspend fun addProduct(product: ProductDbModel)

}