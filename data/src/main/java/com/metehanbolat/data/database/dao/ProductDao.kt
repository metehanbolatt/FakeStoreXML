package com.metehanbolat.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.metehanbolat.domain.model.ProductDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(product: ProductDbModel)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun readAllData(): Flow<List<ProductDbModel>>


}