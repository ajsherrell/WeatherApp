package com.ajsherrell.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Locale


const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

const val API_KEY = "8eecd54126fb52847b1065881d17dc6f"

// lesson: epoch time has to be in milli-seconds for this to work
const val EPOCH_FMT_STR = "EEE, hh:mm aaa"

fun Long.formatDate(): String {
    return SimpleDateFormat(EPOCH_FMT_STR, Locale.getDefault()).format(this * 1000)
}