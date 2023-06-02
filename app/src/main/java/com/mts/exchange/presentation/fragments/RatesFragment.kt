package com.mts.exchange.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.mts.exchange.data.RateInfoResponse
import com.mts.exchange.data.Resource
import com.mts.exchange.data.getRatesNames
import com.mts.exchange.databinding.FragmentRatesBinding
import com.mts.exchange.presentation.ExchangeViewModel
import com.mts.exchange.presentation.adapter.RateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class RatesFragment : Fragment() {

    @Inject
    lateinit var rateAdapter: RateAdapter

    private var _binding: FragmentRatesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExchangeViewModel
    private var fullList = emptyList<String>()
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRatesBinding.inflate(inflater, container, false)
        val tmpViewModel by viewModels<ExchangeViewModel>()
        viewModel = tmpViewModel
        viewModel.getRates()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRatesObserver()
        setupSearchListener()
    }

    private fun setupSearchListener() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {
        job?.cancel()
        job = lifecycleScope.launch(Dispatchers.IO) {
            if (query != null) {
                val filteredList = fullList.filter { it.contains(query.uppercase()) }
                withContext(Dispatchers.Main) {rateAdapter.ratesNames = filteredList}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRatesObserver() {
        viewModel.rates.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    setupRateRecycler(response.data!!)

                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private fun setupRateRecycler(response: RateInfoResponse) {
        binding.ratesRv.apply {
            adapter = rateAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            rateAdapter.rate = response
            fullList = response.ratesResponse.getRatesNames()
            rateAdapter.ratesNames = fullList
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


}