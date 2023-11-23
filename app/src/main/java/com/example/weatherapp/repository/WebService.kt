package com.example.weatherapp.repository

import com.example.weatherapp.models.WeatherListResponse
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.utils.Constansts
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("group")
    fun getWeatherDataList(
        @Query("id") cityIds: String,
        @Query("appid") apiKey: String
    ): WeatherListResponse

    @GET("weather")
    suspend fun getLocalWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): WeatherResponse

}

object RetrofitClient {

    private const val BASE_URL = Constansts.BASE_URL

    val wbeService: WebService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(WebService::class.java)
    }
}