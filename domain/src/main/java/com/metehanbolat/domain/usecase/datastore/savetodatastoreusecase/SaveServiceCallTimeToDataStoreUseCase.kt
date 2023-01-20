package com.metehanbolat.domain.usecase.datastore.savetodatastoreusecase

interface SaveServiceCallTimeToDataStoreUseCase {

    suspend operator fun invoke(serviceCallTime: String)
}