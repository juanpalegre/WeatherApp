package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.CityDataSource
import com.example.weatherapp.models.CityWeather
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.models.CityData

class CityRepositoryImpl(private val cityDataSource: CityDataSource): CityRepository {

    override suspend fun getLocalWeatherData(lat: Double, lon: Double, apiKey: String): CityWeather {
        try {
            val weatherResponse = cityDataSource.getLocalWeatherData(lat, lon, apiKey)
            Log.d("CityRepository", "Local weather data received: $weatherResponse")
            return mapToCityWeather(weatherResponse)
        } catch (e: Exception) {
            Log.e("CityRepository", "Error getting local weather data", e)
            throw e
        }
    }

    override suspend fun getWeatherDataList(cityIds: String, apiKey: String): List<CityWeather> {
        try {
            val weatherResponseList = cityDataSource.getWeatherDataList(cityIds, apiKey)
            Log.d("CityRepository", "Weather data list received: $weatherResponseList")
            return weatherResponseList.map { mapToCityWeather(it) }
        } catch (e: Exception) {
            Log.e("CityRepository", "Error getting weather data list", e)
            throw e
        }
    }

    private fun mapToCityData(weatherResponse: WeatherResponse): CityData {
        return CityData(
            cityName = weatherResponse.name,
            countryName = weatherResponse.sys.country,
            currentTemperature = weatherResponse.main.temp
        )
    }

    private fun mapToCityWeather(weatherResponse: WeatherResponse): CityWeather {
        return CityWeather(
            cityName = weatherResponse.name,
            country = weatherResponse.sys.country,
            temperature = weatherResponse.main.temp,
            weatherDescription = weatherResponse.weather.first().description,
            weatherIcon = weatherResponse.weather.first().icon
        )
    }

}