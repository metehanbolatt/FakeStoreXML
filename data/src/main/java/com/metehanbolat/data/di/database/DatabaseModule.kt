package com.metehanbolat.data.di.database

import android.content.Context
import androidx.room.Room
import com.metehanbolat.data.database.ProductDatabase
import com.metehanbolat.data.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ProductDatabase =
        Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_database"
        ).build()

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao = database.productDao()

}