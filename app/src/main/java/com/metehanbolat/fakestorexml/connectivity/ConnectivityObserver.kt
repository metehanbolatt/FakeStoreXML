package com.metehanbolat.fakestorexml.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observer(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }

}