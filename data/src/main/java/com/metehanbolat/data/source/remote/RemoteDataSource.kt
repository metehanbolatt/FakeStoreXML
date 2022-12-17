package com.metehanbolat.data.source.remote

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem

interface RemoteDataSource {

    suspend fun getAllProducts(): NetworkResponse<List<ProductItem>>
    suspend fun getLimitedProducts(limit: String): NetworkResponse<List<ProductItem>>

}