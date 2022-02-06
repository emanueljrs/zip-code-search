package com.emanuel.zipcodesearch.models

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("cep") val zip: String,
    @SerializedName("logradouro") val street: String,
    @SerializedName("complemento")val complement: String,
    @SerializedName("bairro") val district: String,
    @SerializedName("localidade") val location: String,
    val uf: String,
    val ddd: String
)
