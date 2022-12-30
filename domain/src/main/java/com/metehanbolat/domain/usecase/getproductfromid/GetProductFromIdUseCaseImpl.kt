package com.metehanbolat.domain.usecase.getproductfromid

import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductFromIdUseCaseImpl @Inject constructor(
    private val fakeStoreRepository: FakeStoreRepository
) : GetProductFromIdUseCase {

    override fun invoke(id: String): Flow<NetworkResponse<ProductItem>> =
        flow {
            emit(NetworkResponse.Loading)
            emit(fakeStoreRepository.getProductFromId(id = id))
        }
}