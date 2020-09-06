package com.example.weather.Model.Json.Forecast

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class FortempModel (
    @PrimaryKey
    val id: Int,
    val day : Double,
    val min : Double,
    val max : Double,
    val night : Double,
    val eve : Double,
    val morn : Double
)