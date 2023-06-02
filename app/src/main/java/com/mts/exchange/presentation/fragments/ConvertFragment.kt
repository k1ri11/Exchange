package com.mts.exchange.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.mts.exchange.R
import com.mts.exchange.databinding.FragmentConvertBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConvertFragment : Fragment() {

    private val args by navArgs<ConvertFragmentArgs>()

    private var _binding: FragmentConvertBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConvertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
        setupInputFieldsListeners()
    }

    private fun setupInputFieldsListeners() {

        binding.rubInput.doOnTextChanged { text, _, _, _ ->
            changeValues(binding.rateInput, text)
        }
    }

    private fun changeValues(toInput: TextInputEditText,  text: CharSequence?) {
        if (!text.isNullOrBlank()){
            try {
                val fromValue = text.toString().toBigDecimal()
                val toValue= args.rate.toBigDecimal() * fromValue
                toInput.setText(toValue.toString().format("%.2f"))
            }
            catch (e: NumberFormatException){
                Toast.makeText(requireContext(), "can't calculate this value", Toast.LENGTH_SHORT).show()
            }
        }
        else toInput.setText("")
    }

    private fun updateUI() {
        binding.rateInputLayout.hint = args.rateName
        binding.currentRate.text =
            resources.getString(R.string.current_rate, args.rate, args.rateName)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}