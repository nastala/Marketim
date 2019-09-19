package com.example.marketim.Classes

data class Product (
    val day: String,
    val month: String,
    val marketName: String,
    val orderName: String,
    val price: Double,
    val state: String,
    val details: ProductDetail
)