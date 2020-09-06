@file:Suppress("DEPRECATION")

package com.example.weather.Model.Json.Utilites

import android.content.Context
import android.net.ConnectivityManager

object ConnectionUtils {
    fun isNetworkAvialable(context: Context) : Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val one = cm.activeNetworkInfo !=null
        val two  =  cm.activeNetworkInfo?.isConnected ?: false
        return one && two
    }
}