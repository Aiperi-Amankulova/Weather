package com.example.weather.Model.Json.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weather.Model.Json.Forecast.Forecast
import com.example.weather.Model.Json.Forecast.ForecastModel

@Dao
interface Dao {
    @Insert
    fun add(data : Forecast)

    @Query("SELECT * FROM ForecastModel")
    fun getAll(): LiveData<List<ForecastModel>>

    @Query("DELETE FROM ForecastModel")
    fun deleteAll()

}
