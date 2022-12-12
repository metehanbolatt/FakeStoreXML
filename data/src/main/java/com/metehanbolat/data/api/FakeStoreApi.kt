package com.metehanbolat.data.api

import com.metehanbolat.data.dto.ProductsResponse
import retrofit2.http.GET

interface FakeStoreApi {

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse

}