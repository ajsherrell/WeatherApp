package com.ajsherrell.weatherapp.model

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.collections.List

val context: Context? = null

@JsonClass(generateAdapter = true)
data class Response(
    val cod: String?,
    val message: Int?,
    val cnt: Int?,
    @Json(name = "list")
    val category: List<Category> = mutableListOf(),
    val city: City
)

@JsonClass(generateAdapter = true)
data class City(
    val name: String?,
    val coord: Coord,
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
    val dt: Long?,
    val main: Main,
    val weather: List<Weather> = mutableListOf(),
    val wind: Wind,
    val dt_txt: String?
)

@JsonClass(generateAdapter = true)
data class Main(
    val temp: Double?,
    val temp_min: Double?,
    val temp_max: Double?,
    val humidity: Int?
) {

//    fun getMinMaxTemp(): String? {
//        return toString(temp_min, temp_max)
//    }
//
//    private fun toString(tempMin: Double?, tempMax: Double?): String? {
//        return context?.resources?.getString(R.string.minMinTempString, tempMin, tempMax)
//    }
//
//    fun getTemp(): String? {
//        return toString(temp)
//    }
//
//    private fun toString(temp: Double?): String? {
//        return context?.resources?.getString(R.string.tempString, temp)
//    }
//
//    fun getHumidity(): String? {
//        return toString(humidity)
//    }
//
//    private fun toString(humidity: Int?): String? {
//        //return Resources.getSystem().getString(R.string.humidityString, humidity)
//        return context?.resources?.getString(R.string.humidityString, humidity)
//    }
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

//    fun getWindSpeed(): String? {
//        return toString(speed)
//    }
//
//    private fun toString(speed: Double?): String? {
//        return context?.resources?.getString(R.string.windSpeedString, speed)
//    }

}



