package com.mts.exchange.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mts.exchange.R
import com.mts.exchange.data.ExchangeApi
import com.mts.exchange.data.RateInfoResponse
import com.mts.exchange.data.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: ExchangeApi,
    private val networkUtils: NetworkUtils
) {
    private val _rates = MutableLiveData<Resource<RateInfoResponse>>()
    val rates: LiveData<Resource<RateInfoResponse>> = _rates

    suspend fun getRates(baseRate: String, places: Int) {
        if (networkUtils.hasInternetConnection()){
            _rates.postValue(Resource.Loading())
            val response = api.getRates(baseRate, places)
            if (response.isSuccessful) {
                _rates.postValue(Resource.Success(response.body()!!))
            } else {
                _rates.postValue(Resource.Error(response.message()))
            } 
        }else{
            _rates.postValue(Resource.Error(context.getString(R.string.no_connection)))
        }
    }

}