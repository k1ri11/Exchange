package com.mts.exchange.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApi {

    @GET("/latest?")
    suspend fun getRates(
        @Query("base") baseRate: String,
        @Query("places") places: Int,
        ): Response<RateInfoResponse>
}