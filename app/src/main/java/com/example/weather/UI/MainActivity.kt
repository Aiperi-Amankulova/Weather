package com.example.weather.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import com.example.retrofit.Data.Json.CurrentWeather
import com.example.weather.Data.RetrofitBuilder
import com.example.weather.Model.Json.Forecast.Forecast
import com.example.weather.R
import com.example.weather.Model.Json.Utilites.ConnectionUtils
import com.example.weather.Model.Json.Utilites.PermissionUtils
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val adapter = RvAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        formatDate()
        getSneckbar()

        recyclerView.adapter = adapter

        if (PermissionUtils.checkLocationPermission(this)) {
            LoadLocattion()
        }
    }

    private fun showSnackbars() {
        Snackbar.make(parentLayout, "Нет подключения", Snackbar.LENGTH_INDEFINITE)
            .setAction("Обновить") {
                if (!ConnectionUtils.isNetworkAvialable(this)) {
                    showSnackbars()
                }
            }.show()
    }

    private fun getSneckbar() {
        val isHasNetwork = ConnectionUtils.isNetworkAvialable(this)
        if (!isHasNetwork) {
            showSnackbars()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionUtils.LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                LoadLocattion()
        }
    }

    @SuppressLint("MissingPermission")
    private fun LoadLocattion() {
        val fpc = LocationServices.getFusedLocationProviderClient(applicationContext)

        fpc.lastLocation.addOnSuccessListener {
            LoadByLocation(it)
            LoadByLocationSecond(it)
        }.addOnFailureListener {

        }
    }

    fun LoadByLocation(location: Location) {
        RetrofitBuilder.getService()?.getWeathers(
            location.latitude.toString(), location.longitude.toString(),
            getString(R.string.api), "metric"
        )?.enqueue(object : Callback<CurrentWeather> {
            @SuppressLint("StringFormatInvalid")
            override fun onResponse(
                call: Call<CurrentWeather>,
                response: Response<CurrentWeather>
            ) {
                
                val city = response.body()?.name
                val temp = getString(R.string._18, response.body()?.main?.temp?.toInt().toString())
                val feels = response.body()?.main?.feels_like
                val min = getString(R.string._18, response.body()?.main?.temp_min?.toInt().toString())
                val max = getString(R.string._18, response.body()?.main?.temp_max?.toInt().toString())
                val pressure = getString(R.string._1010mb, response.body()?.main?.pressure.toString())
                val humidity = getString(R.string._81, response.body()?.main?.humidity)
                val cloud = getString(R.string._81, response.body()?.clouds?.all)
                val wind = getString(R.string.sw_4m_s, response.body()?.wind?.speed?.toInt().toString())
                val sunrise = formatDate(response.body()?.sys?.sunrise)
                val sunset = formatDate(response.body()?.sys?.sunset)
                val image = response.body()?.weather?.first()?.icon
                val descript = response.body()?.weather?.first()?.description

                textView.text = city.toString()
                textView3.text = max
                textView4.text = min
                textView10.text = wind
                textView15.text = pressure
                textView12.text = humidity
                textView17.text = cloud
                number2.text = temp
                textView22.text = sunrise
                textView19.text = sunset
                little.text = descript.toString()
                Picasso.get().load("http://openweathermap.org/img/w/$image.png").into(imageView2)
            }

            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                Log.d("sfurhgo1qj", "jhfreuhfeqj")

            }

        })
    }


    private fun formatDate() {
        val tv = SimpleDateFormat("d", Locale.getDefault())
        val date = Date()
        val day = tv.format(date)
        number.text = day
        val sfdMonth = SimpleDateFormat("MMMM\nyyyy", Locale.getDefault())
        val month = sfdMonth.format(date)
        calendarDay.text = month
    }

    fun formatDate(date: Int?): String {
        val newdata = date?.toLong() ?: 0
        return SimpleDateFormat("H:mm", Locale.getDefault()).format(Date(newdata * 1000))
    }

    fun LoadByLocationSecond(location: Location) {
        RetrofitBuilder.getService()?.сall(
            location.latitude.toString(),
            location.longitude.toString(),
            "hourly,current,minutely",
            getString(R.string.api),
            "metric"
        )?.enqueue(object : Callback<Forecast> {
            override fun onResponse(
                call: Call<Forecast>,
                response: Response<Forecast>
            ) {
                if (response.isSuccessful && response.body() != null) {  // troubles
                    adapter.update(response.body()?.daily)
                }

            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.d("jnfjer", "iferugfuierhga")
            }
        })
    }
}