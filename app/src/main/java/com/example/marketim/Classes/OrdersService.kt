package com.example.marketim.Classes

import retrofit2.Call
import retrofit2.http.GET

interface OrdersService {
    @GET(".")
    fun getOrders(): Call<List<Product>>
}