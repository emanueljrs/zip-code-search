package com.emanuel.zipcodesearch.service

import com.emanuel.zipcodesearch.models.Address
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {

    @GET("{cep}/json")
    suspend fun searchAddress(@Path("cep") zip: String): Response<Address>
}