package com.example.weatherapp.repository

import com.example.weatherapp.models.CityWeather
import com.example.weatherapp.models.CityData

interface CityRepository {

    suspend fun getLocalWeatherData(lat: Double, lon: Double, apiKey: String): CityWeather

    suspend fun getWeatherDataList(cityIds: String, apiKey: String): List<CityWeather>
}