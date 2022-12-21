package com.metehanbolat.domain.usecase.addproducttodatabaseusecase

import com.metehanbolat.domain.model.ProductDbModel
import com.metehanbolat.domain.repository.FakeStoreRepository
import javax.inject.Inject

class AddProductToDatabaseUseCaseImpl @Inject constructor(
    private val fakeStoreRepository: FakeStoreRepository
) : AddProductToDatabaseUseCase {

    override suspend fun invoke(product: ProductDbModel) =
        fakeStoreRepository.addProduct(product = product)
}