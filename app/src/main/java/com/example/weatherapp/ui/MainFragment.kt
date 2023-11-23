package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.data.CityDataSource
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.presentation.CityViewModel
import com.example.weatherapp.presentation.CityViewModelFactory
import com.example.weatherapp.repository.CityRepositoryImpl
import com.example.weatherapp.repository.RetrofitClient

/**
 * Fragment destinado a mostrar la pantalla principal para el clima actual
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var cityViewModel: CityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        // Configuramos el ViewModel
        val cityRepository = CityRepositoryImpl(CityDataSource(RetrofitClient.wbeService))
        cityViewModel = ViewModelProvider(this, CityViewModelFactory(cityRepository))
            .get(CityViewModel::class.java)

        // Llamamos a la función para obtener el clima local
        cityViewModel.getLocalWeather(44.34, 10.99)

        // Observamos los cambios en el LiveData
        cityViewModel.localWeather.observe(viewLifecycleOwner) { weather ->
            binding.textView.text = "Current Temperature: ${weather.temperature}°C"
        }
    }


}