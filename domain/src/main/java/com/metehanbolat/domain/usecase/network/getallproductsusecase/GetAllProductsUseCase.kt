package com.metehanbolat.domain.usecase.network.getallproductsusecase

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {

    operator fun invoke(): Flow<NetworkResponse<List<ProductItem>>>

}