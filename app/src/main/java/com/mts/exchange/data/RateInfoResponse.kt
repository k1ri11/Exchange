package com.mts.exchange.data


import com.google.gson.annotations.SerializedName

data class RateInfoResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val ratesResponse: RatesResponse,
    @SerializedName("success")
    val success: Boolean
)