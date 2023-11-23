package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.CityDataSource
import com.example.weatherapp.databinding.FragmentCityListBinding
import com.example.weatherapp.models.CityData
import com.example.weatherapp.models.CityWeather
import com.example.weatherapp.presentation.CityViewModel
import com.example.weatherapp.presentation.CityViewModelFactory
import com.example.weatherapp.repository.CityRepositoryImpl
import com.example.weatherapp.repository.RetrofitClient

/**
 * Fragment destinado a mostrar una lista de cuidades para visualizar el clima
 */

class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private lateinit var binding: FragmentCityListBinding
    private lateinit var cityViewModel: CityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityListBinding.bind(view)

        // Configuramos el ViewModel
        val cityRepository = CityRepositoryImpl(CityDataSource(RetrofitClient.wbeService))
        cityViewModel = ViewModelProvider(this, CityViewModelFactory(cityRepository))
            .get(CityViewModel::class.java)

        // Lista de IDs de ciudades
        val cityIds = "2643743,2988507,1850147"

        // Llamamos a la función para obtener el clima en otras ciudades
        cityViewModel.getOtherCitiesWeather(cityIds)

        // Observamos los cambios en el LiveData
        cityViewModel.otherCitiesWeather.observe(viewLifecycleOwner) { weatherList ->
            // Actualizamos el adaptador del RecyclerView con los datos de las ciudades
            val cityAdapter = CityAdapter()
            binding.recyclerViewCityList.apply {
                adapter = cityAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            cityAdapter.submitList(weatherList.map { mapToCityData(it) })
        }
    }

    private fun mapToCityData(weather: CityWeather): CityData {
        return CityData(
            cityName = weather.cityName,
            countryName = weather.country,
            currentTemperature = weather.temperature
        )
    }

}