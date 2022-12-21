package com.metehanbolat.data.di.database

import android.app.Application
import androidx.room.Room
import com.metehanbolat.data.database.ProductDatabase
import com.metehanbolat.data.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        appContext: Application,
        callback: ProductDatabase.Callback
    ): ProductDatabase =
        Room.databaseBuilder(
            appContext,
            ProductDatabase::class.java,
            "product_database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    @Singleton
    fun provideProductDao(database: ProductDatabase): ProductDao = database.productDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())
}