package com.metehanbolat.domain.usecase.addproducttodatabaseusecase

import com.metehanbolat.domain.model.ProductDbModel

interface AddProductToDatabaseUseCase {

    suspend operator fun invoke(product: ProductDbModel)
}