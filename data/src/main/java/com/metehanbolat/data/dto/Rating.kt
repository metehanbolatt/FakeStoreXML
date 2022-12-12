package com.metehanbolat.data.dto


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
)