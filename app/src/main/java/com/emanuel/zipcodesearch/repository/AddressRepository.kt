package com.emanuel.zipcodesearch.repository

import com.emanuel.zipcodesearch.network.RetrofitInstance

class AddressRepository {
    suspend fun getAddress(zip: String) = RetrofitInstance.retrofit.searchAddress(zip = zip)
}