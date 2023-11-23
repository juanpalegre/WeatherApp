package com.example.weatherapp.models

import com.example.weatherapp.models.WeatherResponse
import com.google.gson.annotations.SerializedName

data class WeatherListResponse(
    @SerializedName("list") val list: List<WeatherResponse>
)
