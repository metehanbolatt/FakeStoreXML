package com.metehanbolat.domain.usecase.getallproductsusecase

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(
    private val fakeStoreRepository: FakeStoreRepository
) : GetAllProductsUseCase {

    override fun invoke(): Flow<NetworkResponse<List<ProductItem>>> =
        flow { emit(fakeStoreRepository.getAllProducts()) }
}