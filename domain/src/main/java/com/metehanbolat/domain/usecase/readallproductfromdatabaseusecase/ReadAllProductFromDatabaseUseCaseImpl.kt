package com.metehanbolat.domain.usecase.readallproductfromdatabaseusecase

import com.metehanbolat.domain.model.ProductDbModel
import com.metehanbolat.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReadAllProductFromDatabaseUseCaseImpl @Inject constructor(
    private val fakeStoreRepository: FakeStoreRepository
) : ReadAllProductFromDatabaseUseCase {

    override fun invoke(): Flow<List<ProductDbModel>> = flow { fakeStoreRepository.readAllData() }
}