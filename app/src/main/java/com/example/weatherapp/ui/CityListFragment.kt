package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityListBinding

/**
 * Fragment destinado a mostrar una lista de cuidades para visualizar el clima
 */

class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private lateinit var binding: FragmentCityListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityListBinding.bind(view)
    }

}