package com.example.weatherapp.repository

import com.example.weatherapp.models.CityData

interface CityRepository {

    fun getCityDataList(): List<CityData>
}