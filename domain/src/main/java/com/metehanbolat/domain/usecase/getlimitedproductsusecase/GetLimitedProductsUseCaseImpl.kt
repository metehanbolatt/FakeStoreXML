package com.metehanbolat.domain.usecase.getlimitedproductsusecase

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLimitedProductsUseCaseImpl @Inject constructor(
    private val fakeStoreRepository: FakeStoreRepository
): GetLimitedProductsUseCase {

    override fun invoke(limit: String): Flow<NetworkResponse<List<ProductItem>>> =
        flow {
            emit(NetworkResponse.Loading)
            emit(fakeStoreRepository.getLimitedProducts(limit = limit))
        }
}