package com.metehanbolat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.metehanbolat.data.database.dao.ProductDao
import com.metehanbolat.domain.model.ProductDbModel

@Database(entities = [ProductDbModel::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

}