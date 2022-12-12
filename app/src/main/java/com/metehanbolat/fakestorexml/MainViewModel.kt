package com.metehanbolat.fakestorexml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.mapper.ProductListMapper
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.usecase.getallproductsusecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val productsMapper: ProductListMapper<ProductItem, MainUIData>
) : ViewModel() {

    private val _mainUIState = MutableLiveData<MainUIState>()
    val mainUIState: LiveData<MainUIState> = _mainUIState

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase().collect { response ->
                when (response) {
                    is NetworkResponse.Loading -> _mainUIState.postValue(MainUIState.Loading)
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