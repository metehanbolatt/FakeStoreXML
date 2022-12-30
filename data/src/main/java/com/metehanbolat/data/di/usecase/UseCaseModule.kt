package com.metehanbolat.data.di.usecase

import com.metehanbolat.domain.usecase.addproducttodatabaseusecase.AddProductToDatabaseUseCase
import com.metehanbolat.domain.usecase.addproducttodatabaseusecase.AddProductToDatabaseUseCaseImpl
import com.metehanbolat.domain.usecase.getallproductsusecase.GetAllProductsUseCase
import com.metehanbolat.domain.usecase.getallproductsusecase.GetAllProductsUseCaseImpl
import com.metehanbolat.domain.usecase.getlimitedproductsusecase.GetLimitedProductsUseCase
import com.metehanbolat.domain.usecase.getlimitedproductsusecase.GetLimitedProductsUseCaseImpl
import com.metehanbolat.domain.usecase.getproductfromid.GetProductFromIdUseCase
import com.metehanbolat.domain.usecase.getproductfromid.GetProductFromIdUseCaseImpl
import com.metehanbolat.domain.usecase.readallproductfromdatabaseusecase.ReadAllProductFromDatabaseUseCase
import com.metehanbolat.domain.usecase.readallproductfromdatabaseusecase.ReadAllProductFromDatabaseUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllProductsUseCase(getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl): GetAllProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetLimitedProductsUseCase(getLimitedProductsUseCaseImpl: GetLimitedProductsUseCaseImpl): GetLimitedProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetProductFromIdUseCase(getProductFromIdUseCaseImpl: GetProductFromIdUseCaseImpl): GetProductFromIdUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindReadAllProductFromDatabaseUseCase(readAllProductFromDatabaseUseCaseImpl: ReadAllProductFromDatabaseUseCaseImpl): ReadAllProductFromDatabaseUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAddProductToDatabaseUseCase(addProductToDatabaseUseCaseImpl: AddProductToDatabaseUseCaseImpl): AddProductToDatabaseUseCase

}