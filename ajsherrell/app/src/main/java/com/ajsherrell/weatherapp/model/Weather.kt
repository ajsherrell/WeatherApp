package com.ajsherrell.weatherapp.model

import kotlin.collections.List

//data class Weather(
//    val dt_txt: String,
//    val temp: Double,
//    val temp_min: Double,
//    val temp_max: Double,
//    val humidity: Int,
//    val main: String,
//    val description: String,
//    val icon: String,
//    val speed: Double
//)

data class List(
    val main: List<Main> = listOf(),
    val weather: List<Weather> = listOf(),
    val wind: List<Wind> = listOf(),
    val dt_txt: String
    )

data class Main(
        val temp: Double,
        var temp_min: Double,
        var temp_max: Double,
        val humidity: Int
    ) {
    fun getMinMaxTemp() {
        toString(temp_min, temp_max)
    }

    private fun toString(tempMin: Double, tempMax: Double): String {
        return "Low: $tempMin / High: $tempMax"
    }
}

data class Weather(
        val main: String,
        val description: String,
        val icon: String
    )

data class Wind(
        val speed: Double
    )


