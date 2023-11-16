package com.example.weatherapp.repository

import com.example.weatherapp.data.CityDataSource
import com.example.weatherapp.models.CityData

class CityRepositoryImpl: CityRepository {
    override fun getCityDataList(): List<CityData> {
        return CityDataSource.getCityData()
    }
}