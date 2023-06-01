package com.mts.exchange.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mts.exchange.data.Resource
import com.mts.exchange.databinding.FragmentRatesBinding
import com.mts.exchange.presentation.ExchangeViewModel
import com.mts.exchange.presentation.RateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RatesFragment : Fragment() {

    @Inject
    lateinit var rateAdapter: RateAdapter

    private var _binding: FragmentRatesBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ExchangeViewModel

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
                    setupRateRecycler()

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

    private fun setupRateRecycler() {

        binding.ratesRv.apply{
            adapter = rateAdapter
            layoutManager =  GridLayoutManager(requireContext(),2);
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


}