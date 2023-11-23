package com.example.weatherapp.models

data class CityWeather(
    val cityName: String,
    val country: String,
    val temperature: Double,
    val weatherDescription: String,
    val weatherIcon: String
)
