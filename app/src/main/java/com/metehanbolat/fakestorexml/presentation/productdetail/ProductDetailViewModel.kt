package com.metehanbolat.fakestorexml.presentation.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehanbolat.domain.common.NetworkResponse
import com.metehanbolat.domain.model.ProductItem
import com.metehanbolat.domain.usecase.getproductfromid.GetProductFromIdUseCase
import com.metehanbolat.fakestorexml.MainUIState
import com.metehanbolat.fakestorexml.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductFromIdUseCase: GetProductFromIdUseCase
): ViewModel() {

    private val _productState = MutableLiveData<MainUIState<ProductItem>>()
    val productState: LiveData<MainUIState<ProductItem>> get() = _productState

    fun getProductFromId(id: String) {
        viewModelScope.launch {
            getProductFromIdUseCase(id = id)
                .onStart { println("getProductFromId: onStart") }
                .onCompletion { println("getProductFromId: onCompletion") }
                .collect { response ->
                    when (response) {
                        NetworkResponse.Loading -> _productState.postValue(MainUIState.Loading)
                        is NetworkResponse.Error -> _productState.postValue(MainUIState.Error(R.string.error))
                        is NetworkResponse.Success -> _productState.postValue(MainUIState.Success(response.result))
                    }
                }
        }
    }

}