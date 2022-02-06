package com.emanuel.zipcodesearch.network

import com.emanuel.zipcodesearch.service.AddressService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofit: AddressService = getRetrofit().create(AddressService::class.java)

}