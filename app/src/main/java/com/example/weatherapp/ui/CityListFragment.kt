package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCityListBinding
import com.example.weatherapp.models.CityData
import com.example.weatherapp.repository.CityRepositoryImpl

/**
 * Fragment destinado a mostrar una lista de cuidades para visualizar el clima
 */

class CityListFragment : Fragment(R.layout.fragment_city_list) {

    private lateinit var binding: FragmentCityListBinding
    private lateinit var cityAdapter: CityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityListBinding.bind(view)

        // Configuración del RecyclerView
        cityAdapter = CityAdapter()
        binding.recyclerViewCityList.apply {
        adapter = cityAdapter
        layoutManager = LinearLayoutManager(requireContext())
        }

        // Obtener datos ficticios (reemplazar con Retrofit más adelante)
        val cityRepository = CityRepositoryImpl()
        val cityList: List<CityData> = cityRepository.getCityDataList()

        // Actualizar el adaptador con los datos
        cityAdapter.submitList(cityList)
    }

}