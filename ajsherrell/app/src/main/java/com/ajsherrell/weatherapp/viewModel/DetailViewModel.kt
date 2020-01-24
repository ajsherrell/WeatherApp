package com.ajsherrell.weatherapp.viewModel

import androidx.lifecycle.MutableLiveData
import com.ajsherrell.weatherapp.base.BaseViewModel
import com.ajsherrell.weatherapp.model.Category
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather
import com.ajsherrell.weatherapp.model.Wind

class DetailViewModel:BaseViewModel() {

    private lateinit var category: Category
    private lateinit var weather: Weather
    private lateinit var main: Main
    private lateinit var wind: Wind

    private val shortDescription = MutableLiveData<String>()
    private val masterTemp = MutableLiveData<String>()
    private val weatherIcon = MutableLiveData<String>()
    private val minMaxTemp = MutableLiveData<String>()
    private val day = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val humidity = MutableLiveData<String>()
    private val windSpeed = MutableLiveData<String>()

    fun getDetailListShortDescription() {
        shortDescription.value = weather.main
    }

    fun getDetailListIcon() {
        weatherIcon.value = weather.icon
    }

    fun getDetailListDescription() {
        description.value = weather.description
    }

    fun getDetailListTemp() {
        masterTemp.value = main.getTemp()
    }

    fun getDetailListMinMaxTemp() {
        minMaxTemp.value = main.getMinMaxTemp()
    }

    fun getDetailListHumidity() {
        humidity.value = main.getHumidity()
    }

    fun getDetailListDay() {
        day.value = category.dt_txt
    }

    fun getDetailListWindSpeed() {
        windSpeed.value = wind.getWindSpeed()
    }
}