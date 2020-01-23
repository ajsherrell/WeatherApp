package com.ajsherrell.weatherapp.viewModel

import androidx.lifecycle.MutableLiveData
import com.ajsherrell.weatherapp.base.BaseViewModel
import com.ajsherrell.weatherapp.model.Category
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather
import com.ajsherrell.weatherapp.model.Wind

class DetailViewModel:BaseViewModel() {

    private val shortDescription = MutableLiveData<String>()
    private val masterTemp = MutableLiveData<String>()
    private val weatherIcon = MutableLiveData<String>()
    private val minMaxTemp = MutableLiveData<String>()
    private val day = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val humidity = MutableLiveData<String>()
    private val windSpeed = MutableLiveData<String>()

    fun bindWeather(weather: Weather) {
        shortDescription.value = weather.main
        weatherIcon.value = weather.icon
        description.value = weather.description
    }

    fun getDetailListShortDescription(): MutableLiveData<String> {
        return shortDescription
    }

    fun getDetailListIcon(): MutableLiveData<String> {
        return weatherIcon
    }

    fun getDetailListDescription(): MutableLiveData<String> {
        return description
    }

    fun bindMain(main: Main) {
        masterTemp.value = main.getTemp()
        minMaxTemp.value = main.getMinMaxTemp()
        humidity.value = main.getHumidity()
    }

    fun getDetailListTemp(): MutableLiveData<String> {
        return masterTemp
    }

    fun getDetailListMinMaxTemp(): MutableLiveData<String> {
        return minMaxTemp
    }

    fun getDetailListHumidity(): MutableLiveData<String> {
        return humidity
    }

    fun bindList(category: Category) {
        day.value = category.dt_txt
    }

    fun getDetailListDay(): MutableLiveData<String> {
        return day
    }

    fun bindWind(wind: Wind) {
        windSpeed.value = wind.getWindSpeed()
    }

    fun getDetailListWindSpeed(): MutableLiveData<String> {
        return windSpeed
    }

}