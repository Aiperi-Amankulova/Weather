package com.example.weather.Model.Json.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.weather.Model.Json.Forecast.*


@Database (entities = [CityModel::class, CoordModel::class, ForecastModel::class,
    Forecast::class, FortempModel::class], version = 1, exportSchema = false)
@TypeConverters(value = [TypeConverter::class])
abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): Dao

}