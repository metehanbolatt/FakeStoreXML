package com.metehanbolat.domain.usecase.addproducttodatabaseusecase

import com.metehanbolat.domain.model.ProductDbModel
import kotlinx.coroutines.flow.Flow

interface AddProductToDatabaseUseCase {

    operator fun invoke(product: ProductDbModel): Flow<ProductDbModel>
}