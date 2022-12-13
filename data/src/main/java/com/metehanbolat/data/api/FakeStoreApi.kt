package com.metehanbolat.data.api

import com.metehanbolat.data.dto.ProductResponseItem
import retrofit2.http.GET

interface FakeStoreApi {

    @GET("products")
    suspend fun getAllProducts(): List<ProductResponseItem>

}