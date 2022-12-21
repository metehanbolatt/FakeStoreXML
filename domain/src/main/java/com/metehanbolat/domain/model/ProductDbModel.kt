package com.metehanbolat.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val productName: String,
    val productImageUrl: String
)