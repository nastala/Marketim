package com.example.marketim.Classes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getClient(): Retrofit {
            return Retrofit.Builder().baseUrl(Helper.URL_ORDERS)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}