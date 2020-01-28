package com.ajsherrell.weatherapp.viewModel

import androidx.lifecycle.MutableLiveData
import com.ajsherrell.weatherapp.base.BaseViewModel
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Wind

class DetailViewModel:BaseViewModel() {

    private var main: Main? = null
    private var wind: Wind? = null

    private val shortDescription = MutableLiveData<String?>()
    private val masterTemp = MutableLiveData<String?>()
    private val weatherIcon = MutableLiveData<String?>()
    private val minMaxTemp = MutableLiveData<String?>()
    private val day = MutableLiveData<String?>()
    private val description = MutableLiveData<String?>()
    private val humidity = MutableLiveData<String?>()
    private val windSpeed = MutableLiveData<String?>()

    fun getDetailListShortDescription():MutableLiveData<String?> {
        return shortDescription
    }

    fun getDetailListIcon():MutableLiveData<String?> {
       return weatherIcon
    }

    fun getDetailListDescription():MutableLiveData<String?> {
        return description
    }

    fun getDetailListTemp():MutableLiveData<String?> {
        masterTemp.value = main?.getTemp()
        return masterTemp
    }

    fun getDetailListMinMaxTemp():MutableLiveData<String?> {
        minMaxTemp.value = main?.getMinMaxTemp()
        return minMaxTemp
    }

    fun getDetailListHumidity():MutableLiveData<String?> {
        humidity.value = main?.getHumidity()
        return humidity
    }

    fun getDetailListDay():MutableLiveData<String?> {
       return day
    }

    fun getDetailListWindSpeed():MutableLiveData<String?> {
        windSpeed.value = wind?.getWindSpeed()
        return windSpeed
    }
}