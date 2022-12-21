package com.metehanbolat.domain.usecase.addproducttodatabaseusecase

import com.metehanbolat.domain.model.ProductDbModel
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductToDatabaseUseCaseImpl @Inject constructor(
    private val fakeStoreRepository: FakeStoreRepository
) : AddProductToDatabaseUseCase {

    override fun invoke(product: ProductDbModel): Flow<ProductDbModel> =
        flow { fakeStoreRepository.addProduct(product = product) }
}