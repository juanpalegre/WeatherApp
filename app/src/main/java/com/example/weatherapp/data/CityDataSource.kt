package com.example.weatherapp.data

import com.example.weatherapp.models.CityData

object CityDataSource {
    fun getCityData(): List<CityData>{
        return listOf(
            CityData("Ciudad 1", "País 1", 25.0, "12:00 PM"),
            CityData("Ciudad 2", "País 2", 20.5, "01:30 PM"),
            CityData("Ciudad 3", "País 3", 22.3, "03:45 PM"),
            CityData("Ciudad 1", "País 1", 25.0, "12:00 PM"),
            CityData("Ciudad 2", "País 2", 20.5, "01:30 PM"),
            CityData("Ciudad 3", "País 3", 22.3, "03:45 PM"),
        )
    }
}
