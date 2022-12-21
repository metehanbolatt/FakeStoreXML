package com.metehanbolat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.metehanbolat.data.database.dao.ProductDao
import com.metehanbolat.data.di.database.ApplicationScope
import com.metehanbolat.domain.model.ProductDbModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [ProductDbModel::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    class Callback @Inject constructor(
        private val database: Provider<ProductDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ): RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().productDao()
            applicationScope.launch {
                dao.addProduct(
                    ProductDbModel(
                        id = 0,
                        productName = "Clothes",
                        productImageUrl = "dummy url"
                    )
                )
            }
        }
    }
}