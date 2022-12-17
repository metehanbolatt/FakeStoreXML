package com.metehanbolat.domain.repository

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem

interface FakeStoreRepository {

    suspend fun getAllProducts(): NetworkResponse<List<ProductItem>>
    suspend fun getLimitedProducts(limit: String): NetworkResponse<List<ProductItem>>

}