package com.example.weather.Data
import com.example.retrofit.Data.Json.CurrentWeather
import com.example.weather.Model.Json.Forecast.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String

    ) : Call<CurrentWeather>

    @GET("data/2.5/weather")
    fun getWeathers(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String,
        @Query("units") units : String
    ) : Call<CurrentWeather>


    @GET("data/2.5/forecast")
    fun forecast(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units : String
    ) : Call<Forecast>


    @GET("data/2.5/onecall")
    fun сall(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude : String,
        @Query("appid") appid: String,
        @Query("units") units : String
    ) : Call<Forecast>

}