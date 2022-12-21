package com.metehanbolat.data.source.local

import androidx.lifecycle.LiveData
import com.metehanbolat.domain.model.ProductDbModel

interface LocalDataSource {

    val readAllData: LiveData<List<ProductDbModel>>
    suspend fun addProduct(product: ProductDbModel)
}