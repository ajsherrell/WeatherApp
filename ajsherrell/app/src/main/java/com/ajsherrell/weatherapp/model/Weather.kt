package com.ajsherrell.weatherapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlin.collections.List

data class List(
    var main: List<Main> = listOf(),
    var weather: List<Weather> = listOf(),
    var wind: List<Wind> = listOf(),
    var dt_txt: String
    )

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
        return "Low: $tempMin / High: $tempMax"
    }

    fun getHumidity(): String {
       return toString(humidity)
    }

    private fun toString(humidity: Int): String {
        return "$humidity%"
    }
}

data class Weather(
        var main: String,
        var description: String,
        var icon: String
    )

data class Wind(
        var speed: Double
    ) {

    fun getWindSpeed(): String {
       return toString(speed)
    }

    private fun toString(speed: Double): String {
        return "Wind Speed: $speed mph"
    }

}


