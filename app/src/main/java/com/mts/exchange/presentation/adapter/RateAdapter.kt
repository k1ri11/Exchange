package com.mts.exchange.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mts.exchange.data.RateInfoResponse
import com.mts.exchange.data.getRateByName
import com.mts.exchange.databinding.ItemRateBinding
import com.mts.exchange.presentation.fragments.RatesFragmentDirections
import javax.inject.Inject

class RateAdapter @Inject constructor(
    private val fragment: Fragment
) : RecyclerView.Adapter<RateAdapter.RateViewHolder>() {

    lateinit var rate: RateInfoResponse
    var ratesNames: List<String> = emptyList()
        set(newValue) {
            val diffCallback = DiffUtilRateCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    inner class RateViewHolder(val binding: ItemRateBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding = ItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RateViewHolder(binding)
    }

    override fun getItemCount(): Int = ratesNames.size

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val currentItem = ratesNames[position]
        holder.binding.apply {
            val rateValue = rate.ratesResponse.getRateByName(currentItem)
            root.setOnClickListener {
                val action = RatesFragmentDirections.actionRatesFragmentToConvertFragment(
                    rate= rateValue.toFloat(),
                    rateName = currentItem
                )
                findNavController(fragment).navigate(action)
            }
            rateName.text = currentItem
            rateCost.text = rateValue.toString()
        }
    }
}