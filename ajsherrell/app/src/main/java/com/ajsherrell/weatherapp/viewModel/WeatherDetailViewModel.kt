package com.ajsherrell.weatherapp.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather
import com.ajsherrell.weatherapp.model.Wind
import com.ajsherrell.weatherapp.model.List

class WeatherDetailViewModel() : ViewModel(), LifecycleObserver {

    val icon = ObservableField<Weather>()

    val dt_txt = ObservableField<String>("2020 Feb, 16") //todo: format date

    val main = ObservableField<String>("cloudy") //short description

    val description = ObservableField<String>("very cloudy all day") //long weather description

    val temp = ObservableField<Double>(34.6)

    val temp_min = ObservableField<Double>(22.2)

    val temp_max = ObservableField<Double>(46.6)

    val humidity = ObservableField<Int>(39)

    val speed = ObservableField<Double>(16.5)

    //todo: setters?
}
