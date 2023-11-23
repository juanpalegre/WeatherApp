package com.example.weatherapp.data

import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.repository.WebService
import com.example.weatherapp.utils.Constansts

class CityDataSource(private val webService: WebService) {

    suspend fun getLocalWeatherData(lat: Double, lon: Double, apiKey: String): WeatherResponse {
        return webService.getLocalWeatherData(lat, lon, Constansts.API_KEY)
    }
    suspend fun getWeatherDataList(cityIds: String, apiKey: String): List<WeatherResponse> {
        return webService.getWeatherDataList(cityIds, Constansts.API_KEY).list
    }
}
