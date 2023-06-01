package com.mts.exchange.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mts.exchange.data.ExchangeApi
import com.mts.exchange.data.RateResponse
import com.mts.exchange.data.Resource
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val api: ExchangeApi
) {
    private val _rates = MutableLiveData<Resource<RateResponse>>()
    val rates: LiveData<Resource<RateResponse>> = _rates

    suspend fun getRates(baseRate: String, places: Int){
        _rates.postValue(Resource.Loading())
        val response = api.getRates(baseRate, places)
        if (response.isSuccessful){
            _rates.postValue(Resource.Success(response.body()!!))
        }
        else {
            _rates.postValue(Resource.Error(response.message()))
        }
    }

}