package com.metehanbolat.fakestorexml.presentation.allproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.mapper.ProductListMapper
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.usecase.getallproductsusecase.GetAllProductsUseCase
import com.metehanbolat.domain.usecase.getlimitedproductsusecase.GetLimitedProductsUseCase
import com.metehanbolat.fakestorexml.ProductUIData
import com.metehanbolat.fakestorexml.MainUIState
import com.metehanbolat.fakestorexml.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getLimitedProductsUseCase: GetLimitedProductsUseCase,
    private val productsMapper: ProductListMapper<ProductItem, ProductUIData>
) : ViewModel() {

    private val _mainUIState = MutableLiveData<MainUIState>()
    val mainUIState: LiveData<MainUIState> = _mainUIState

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase()
                .onStart { println("onStart") }
                .onCompletion { println("onCompletion") }
                .collect { response ->
                    when (response) {
                        NetworkResponse.Loading -> _mainUIState.postValue(MainUIState.Loading)
                        is NetworkResponse.Error -> _mainUIState.postValue(MainUIState.Error(R.string.error))
                        is NetworkResponse.Success -> _mainUIState.postValue(
                            MainUIState.Success(
                                productsMapper.map(response.result)
                            )
                        )
                    }
                }
        }
    }

    fun getLimitedProducts(limit: String) {
        viewModelScope.launch {
            getLimitedProductsUseCase.invoke(limit = limit)
                .onStart { println("getLimitedProducts: onStart") }
                .onCompletion { println("getLimitedProducts: onCompletion") }
                .collect { response ->
                    when (response) {
                        NetworkResponse.Loading -> _mainUIState.postValue(MainUIState.Loading)
                        is NetworkResponse.Error -> _mainUIState.postValue(MainUIState.Error(R.string.error))
                        is NetworkResponse.Success -> _mainUIState.postValue(
                            MainUIState.Success(
                                productsMapper.map(response.result)
                            )
                        )
                    }
                }
        }
    }
}