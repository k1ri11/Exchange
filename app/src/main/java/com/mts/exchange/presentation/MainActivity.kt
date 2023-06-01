package com.mts.exchange.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mts.exchange.R
import com.mts.exchange.databinding.ActivityMainBinding
import com.mts.exchange.domain.ExchangeRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}