package com.example.weatherapp.presentation

import android.provider.SyncStateContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.CityWeather
import com.example.weatherapp.repository.CityRepository
import com.example.weatherapp.utils.Constansts
import kotlinx.coroutines.launch

class CityViewModel(private val cityRepository: CityRepository): ViewModel() {

    private val _localWeather = MutableLiveData<CityWeather>()
    val localWeather: LiveData<CityWeather>
        get() = _localWeather

    private val _otherCitiesWeather = MutableLiveData<List<CityWeather>>()
    val otherCitiesWeather: LiveData<List<CityWeather>>
        get() = _otherCitiesWeather

    // La función para obtener el clima actual local
    fun getLocalWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val weather = cityRepository.getLocalWeatherData(lat, lon, Constansts.API_KEY)
                _localWeather.value = weather
            } catch (e: Exception) {
                Log.e("CityViewModel", "Error getting local weather", e)
            }
        }
    }

    // La función para obtener el clima en otras ciudades
    fun getOtherCitiesWeather(cityIds: String) {
        viewModelScope.launch {
            try {
                val weatherList = cityRepository.getWeatherDataList(cityIds, Constansts.API_KEY)
                _otherCitiesWeather.value = weatherList
            } catch (e: Exception) {
                Log.e("CityViewModel", "Error getting other cities weather", e)
            }
        }
    }
}

class CityViewModelFactory(private val cityRepository: CityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CityRepository::class.java).newInstance(cityRepository)
    }
}