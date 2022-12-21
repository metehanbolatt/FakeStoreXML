package com.metehanbolat.data.source.local

import androidx.lifecycle.LiveData
import com.metehanbolat.data.database.dao.ProductDao
import com.metehanbolat.domain.model.ProductDbModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: ProductDao
) : LocalDataSource {

    override val readAllData: LiveData<List<ProductDbModel>>
        get() = dao.readAllData()

    override suspend fun addProduct(product: ProductDbModel) {
        dao.addProduct(product)
    }
}