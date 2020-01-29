package com.ajsherrell.weatherapp.model

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
)

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
)





