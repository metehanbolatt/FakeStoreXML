package com.metehanbolat.fakestorexml.di.mapper

import com.metehanbolat.domain.mapper.ProductListMapper
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.fakestorexml.MainUIData
import com.metehanbolat.fakestorexml.mapper.ProductMainUIMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindProductMainUIMapper(productMainUIMapperImpl: ProductMainUIMapperImpl): ProductListMapper<ProductItem, MainUIData>
}