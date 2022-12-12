package com.metehanbolat.domain.mapper

interface ProductListMapper<I, O>: ProductMapper<List<I>, List<O>>

interface ProductMapper<I, O> {
    fun map(input: I?): O
}