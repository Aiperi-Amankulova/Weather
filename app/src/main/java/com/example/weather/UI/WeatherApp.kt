package com.example.weather.UI

import android.app.Application
import androidx.room.Room
import com.example.weather.Model.Json.Room.DataBase

class WeatherApp : Application() {

    private val db : DataBase? = null

    override fun onCreate() {
        super.onCreate()
        app = this

        val db = Room.databaseBuilder(applicationContext, DataBase::class.java, DB_Name)
            .allowMainThreadQueries()
            .build()
    }

    fun getDB() = db

    companion object {
        private var app: WeatherApp? = null
        fun getApp() = app
        private const val DB_Name = "MY_DB"
    }
}