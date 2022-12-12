package com.metehanbolat.fakestorexml.mapper

import com.metehanbolat.domain.mapper.ProductListMapper
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.fakestorexml.MainUIData
import javax.inject.Inject

class ProductMainUIMapperImpl @Inject constructor() : ProductListMapper<ProductItem, MainUIData> {
    override fun map(input: List<ProductItem>?): List<MainUIData> {
        return input?.map {
            MainUIData(
                name = it.title ?: "",
                imageUrl = it.image ?: ""
            )
        } ?: emptyList()
    }
}