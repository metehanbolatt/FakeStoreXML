package com.metehanbolat.domain.usecase.datastore.savetodatastoreusecase

import com.metehanbolat.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveServiceCallTimeToDataStoreUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : SaveServiceCallTimeToDataStoreUseCase {

    override suspend fun invoke(serviceCallTime: String) =
        dataStoreRepository.saveToDataStore(serviceCallTime = serviceCallTime)
}