package com.example.weather.Model.Json.Forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.retrofit.Data.Json.Weather
import com.google.gson.annotations.SerializedName
import java.sql.RowId

@Entity
data class ForecastModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val pressure : Int,
    val humidity : Int,
    val dew_point : Double,
    val wind_speed: Double,
    val wind_deg:Int,
    val clouds : Int,
    val pop : Double,
    val rain : Double,
    val uvi : Double,
    @SerializedName("weather") val weather : List<Weather>,
    val temp : FortempModel
)