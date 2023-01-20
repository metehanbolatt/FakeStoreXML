package com.metehanbolat.domain.usecase.datastore.readfromdatastore

import kotlinx.coroutines.flow.Flow

interface ReadServiceCallTimeFromDataStoreUseCase {

    operator fun invoke(): Flow<String>

}