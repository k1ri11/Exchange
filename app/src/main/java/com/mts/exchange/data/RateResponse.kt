package com.mts.exchange.data


import com.google.gson.annotations.SerializedName

data class RateResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Rates,
    @SerializedName("success")
    val success: Boolean
)