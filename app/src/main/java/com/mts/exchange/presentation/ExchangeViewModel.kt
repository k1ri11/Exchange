package com.mts.exchange.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mts.exchange.data.RateResponse
import com.mts.exchange.data.Resource
import com.mts.exchange.domain.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val repository: ExchangeRepository
) : ViewModel() {

    val rates: LiveData<Resource<RateResponse>> = repository.rates

    init {
        getRates()
    }

    fun getRates(baseRate: String = "RUB", places: Int = 3) = viewModelScope.launch(Dispatchers.IO) {
        repository.getRates(baseRate, places)
    }
}