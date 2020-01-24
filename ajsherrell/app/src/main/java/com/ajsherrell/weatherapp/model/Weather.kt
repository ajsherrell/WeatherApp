package com.ajsherrell.weatherapp.model

import android.content.res.Resources
import com.ajsherrell.weatherapp.R
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.collections.List

class Model {
    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "main")
        var main: List<Main> = mutableListOf(),
        @Json(name = "weather")
        var weather: List<Weather> = mutableListOf(),
        @Json(name = "wind")
        var wind: List<Wind> = mutableListOf(),
        @Json(name = "dt_txt")
        var dt_txt: String
    )

    @JsonClass(generateAdapter = true)
    data class Main(
        @Json(name = "temp")
        var temp: Double,
        @Json(name = "temp_min")
        var temp_min: Double,
        @Json(name = "temp_max")
        var temp_max: Double,
        @Json(name = "humidity")
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
        @Json(name = "main")
        var main: String,
        @Json(name = "description")
        var description: String,
        @Json(name = "icon")
        var icon: String
    )

    @JsonClass(generateAdapter = true)
    data class Wind(
        @Json(name = "speed")
        var speed: Double
    ) {

        fun getWindSpeed(): String {
            return toString(speed)
        }

        private fun toString(speed: Double): String {
            return Resources.getSystem().getString(R.string.windSpeedString, speed)
        }

    }
}


