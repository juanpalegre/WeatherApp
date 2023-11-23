package com.example.weatherapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemCityBinding
import com.example.weatherapp.models.CityData

class CityAdapter : ListAdapter<CityData, CityViewHolder>(CityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CityViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cityData: CityData) {
        binding.textCityName.text = cityData.cityName
        binding.textCountryName.text = cityData.countryName
        binding.textTemperature.text = "Temperature: ${cityData.currentTemperature}°C"
    }
}

class CityDiffCallback : DiffUtil.ItemCallback<CityData>() {
    override fun areItemsTheSame(oldItem: CityData, newItem: CityData): Boolean {
        return oldItem.cityName == newItem.cityName
    }

    override fun areContentsTheSame(oldItem: CityData, newItem: CityData): Boolean {
        return oldItem == newItem
    }
}