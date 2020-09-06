package com.example.weather.Model.Json.Forecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoordModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val lat: Double,
    val ion : Double
)