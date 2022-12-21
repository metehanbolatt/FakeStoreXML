package com.metehanbolat.domain.usecase.readallproductfromdatabaseusecase

import com.metehanbolat.domain.model.ProductDbModel
import kotlinx.coroutines.flow.Flow

interface ReadAllProductFromDatabaseUseCase {

    operator fun invoke(): Flow<List<ProductDbModel>>

}