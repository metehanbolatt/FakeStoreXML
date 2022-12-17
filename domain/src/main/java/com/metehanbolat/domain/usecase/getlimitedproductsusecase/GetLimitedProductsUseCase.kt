package com.metehanbolat.domain.usecase.getlimitedproductsusecase

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface GetLimitedProductsUseCase {

    operator fun invoke(limit: String): Flow<NetworkResponse<List<ProductItem>>>

}