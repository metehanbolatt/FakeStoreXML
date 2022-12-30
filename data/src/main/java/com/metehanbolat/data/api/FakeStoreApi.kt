package com.metehanbolat.data.api

import com.metehanbolat.data.dto.ProductResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FakeStoreApi {

    @GET("products")
    suspend fun getAllProducts(): List<ProductResponseItem>

    @GET("products")
    suspend fun getLimitedProducts(@Query("limit") limit: String): List<ProductResponseItem>

    @GET("products/{id}")
    suspend fun getProductFromId(@Path("id") id: String): ProductResponseItem

}