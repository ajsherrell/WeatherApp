package com.ajsherrell.weatherapp.model

import android.content.res.Resources
import com.ajsherrell.weatherapp.R
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.collections.List

@JsonClass(generateAdapter = true)
data class Response(
    val cod: String?,
    val message: Int?,
    val cnt: Int?,
    @Json(name = "list")
    val category: List<Category> = mutableListOf(),
    val city: List<City> = mutableListOf()
)

@JsonClass(generateAdapter = true)
data class City(
    val name: String?,
    val coord: List<Coord> = mutableListOf(),
    val country: String?,
    val timeZone: Long?,
    val sunrise: Long?,
    val sunset: Long?
)

@JsonClass(generateAdapter = true)
data class Coord(
    val lat: Double?,
    val lon: Double?
)

@JsonClass(generateAdapter = true)
@Json(name = "list")
data class Category(
    val main: List<Main> = mutableListOf(),
    val weather: List<Weather> = mutableListOf(),
    val wind: List<Wind> = mutableListOf(),
    val dt_txt: String?
)

@JsonClass(generateAdapter = true)
data class Main(
    val temp: Double?,
    val temp_min: Double?,
    val temp_max: Double?,
    val humidity: Int?
) {

    fun getMinMaxTemp(): String {
        return toString(temp_min, temp_max)
    }

    private fun toString(tempMin: Double?, tempMax: Double?): String {
        return Resources.getSystem().getString(R.string.minMinTempString, tempMin, tempMax)
    }

    fun getTemp(): String {
        return toString(temp)
    }

    private fun toString(temp: Double?): String {
        return Resources.getSystem().getString(R.string.tempString, temp)
    }

    fun getHumidity(): String {
        return toString(humidity)
    }

    private fun toString(humidity: Int?): String {
        return Resources.getSystem().getString(R.string.humidityString, humidity)
    }
}

@JsonClass(generateAdapter = true)
data class Weather(
    val main: String?,
    val description: String?,
    val icon: String?
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "speed")
    val speed: Double?
) {

    fun getWindSpeed(): String {
        return toString(speed)
    }

    private fun toString(speed: Double?): String {
        return Resources.getSystem().getString(R.string.windSpeedString, speed)
    }

}



