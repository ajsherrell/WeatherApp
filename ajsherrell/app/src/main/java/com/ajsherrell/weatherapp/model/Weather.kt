package com.ajsherrell.weatherapp.model

import android.content.res.Resources
import com.ajsherrell.weatherapp.R
import com.squareup.moshi.JsonClass
import kotlin.collections.List

@JsonClass(generateAdapter = true)
data class Category(
    var main: List<Main> = listOf(),
    var weather: List<Weather> = listOf(),
    var wind: List<Wind> = listOf(),
    var dt_txt: String
    )

@JsonClass(generateAdapter = true)
data class Main(
        var temp: Double,
        var temp_min: Double,
        var temp_max: Double,
        var humidity: Int
    ) {

    fun getMinMaxTemp(): String {
        return toString(temp_min, temp_max)
    }

    private fun toString(tempMin: Double, tempMax: Double): String {
        return Resources.getSystem().getString(R.string.minMinTempString, tempMin, tempMax)
    }

    fun getTemp(): String {
        return toString(temp)
    }

    private fun toString(temp: Double): String {
        return Resources.getSystem().getString(R.string.tempString, temp)
    }

    fun getHumidity(): String {
       return toString(humidity)
    }

    private fun toString(humidity: Int): String {
        return Resources.getSystem().getString(R.string.humidityString, humidity)
    }
}

@JsonClass(generateAdapter = true)
data class Weather(
        var main: String,
        var description: String,
        var icon: String
    )

@JsonClass(generateAdapter = true)
data class Wind(
        var speed: Double
    ) {

    fun getWindSpeed(): String {
       return toString(speed)
    }

    private fun toString(speed: Double): String {
        return Resources.getSystem().getString(R.string.windSpeedString, speed)
    }

}


