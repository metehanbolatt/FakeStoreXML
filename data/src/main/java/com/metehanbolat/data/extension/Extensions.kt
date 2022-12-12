package com.metehanbolat.data.extension

import com.metehanbolat.data.dto.ProductResponseItem
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.model.Rating

fun ProductResponseItem.toProductItem(): ProductItem {
    val rating = rating?.let { Rating(it.count, it.rate) }
    return ProductItem(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating,
        title = title
    )
}