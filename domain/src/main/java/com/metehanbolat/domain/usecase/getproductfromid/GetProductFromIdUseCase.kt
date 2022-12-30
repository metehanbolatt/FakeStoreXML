package com.metehanbolat.domain.usecase.getproductfromid

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface GetProductFromIdUseCase {

    operator fun invoke(id: String): Flow<NetworkResponse<ProductItem>>
}