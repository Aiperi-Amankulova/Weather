package com.example.weather.Model.Json.Forecast


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Forecast(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cod: String,
    val message: Int,
    val cnt: Int,
    val daily: List<ForecastModel>
)