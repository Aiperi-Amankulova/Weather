package com.example.weather.Data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private var service : WeatherService? = null

    fun getService() : WeatherService? {
        if ( service == null )
            service = buildRetrofit()
        return service }

    private fun  buildRetrofit(): WeatherService {

        val service =
            Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttp())
                .build()
                .create(WeatherService:: class.java)
        return service
    }

    private fun buildOkHttp(): OkHttpClient {
        val okhttp = OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()
        return okhttp
    }
}