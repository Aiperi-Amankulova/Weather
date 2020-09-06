package com.example.weather.Model.Json.Room

import android.text.TextUtils
import androidx.room.TypeConverter
import com.example.retrofit.Data.Json.Weather
import com.example.weather.Model.Json.Forecast.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {

    //Object
    @JvmStatic
    @TypeConverter
    fun CoordtoString(model: CoordModel): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun coordtoObject(text: String): CoordModel? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, CoordModel::class.java)
    }


    @JvmStatic
    @TypeConverter
    fun citytoString(model: CityModel): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun citytoObject(text: String): CityModel? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, CityModel::class.java)
    }

    @JvmStatic
    @TypeConverter
    fun ForcastModelOnetoString(model: Forecast): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun ForcastModelOnetoObject(text: String): Forecast? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, Forecast::class.java)
    }
    @JvmStatic
    @TypeConverter
    fun ForCastDailyFourthtoString(model: ForecastModel): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun ForCastDailyFourthtoObject(text: String): ForecastModel? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, ForecastModel::class.java)
    }

    @JvmStatic
    @TypeConverter
    fun ForTempFifthtoString(model: FortempModel): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun ForTempFifthtoObject(text: String): FortempModel? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, FortempModel::class.java)
    }
    @JvmStatic
    @TypeConverter
    fun listForCastDailyFourthToString(model: List<ForecastModel>): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun listForCastDailyFourthToObject(text: String?): List<ForecastModel>? {
        if (text == null) return mutableListOf()
        val typeToken = object : TypeToken<List<ForecastModel>>() {}.type
        return Gson().fromJson(text, typeToken)
    }
    // //array of object
    @JvmStatic
    @TypeConverter
    fun weatherToString(model: List<Weather>): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun weatherToObject(text: String?): List<Weather>? {
        if (text == null) return mutableListOf()
        val typeToken = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(text, typeToken)
    }
}