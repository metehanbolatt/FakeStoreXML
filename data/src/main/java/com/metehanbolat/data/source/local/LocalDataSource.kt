package com.metehanbolat.data.source.local

import com.metehanbolat.domain.model.ProductDbModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    val readAllData: Flow<List<ProductDbModel>>
    suspend fun addProduct(product: ProductDbModel)
}