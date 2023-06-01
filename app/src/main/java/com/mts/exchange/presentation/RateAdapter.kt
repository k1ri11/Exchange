package com.mts.exchange.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mts.exchange.R
import com.mts.exchange.data.Rates
import com.mts.exchange.databinding.ItemRateBinding
import com.mts.exchange.presentation.fragments.RatesFragmentDirections
import javax.inject.Inject

class RateAdapter @Inject constructor(
    private val fragment: Fragment
) : RecyclerView.Adapter<RateAdapter.RateViewHolder>() {

    var ratesList: List<Rates> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    var ratesListTest: List<Pair<String, Float>> =
        listOf(Pair("sns", 0.24f), Pair("sns", 0.24f), Pair("sns", 0.24f), Pair("sns", 0.24f))

    inner class RateViewHolder(val binding: ItemRateBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding = ItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RateViewHolder(binding)
    }

    override fun getItemCount(): Int = ratesListTest.size

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val currentItem = ratesListTest[position]
        holder.binding.apply {
            root.setOnClickListener {
                val action = RatesFragmentDirections.actionRatesFragmentToConvertFragment(currentItem.first, currentItem.second)
                findNavController(fragment).navigate(action)
            }
            rateName.text = currentItem.first
            rateCost.text = currentItem.second.toString()
        }
    }
}