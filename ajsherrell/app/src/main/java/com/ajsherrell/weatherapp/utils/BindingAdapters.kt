package com.ajsherrell.weatherapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.utils.extension.getParentActivity

@BindingAdapter("openWeatherIcon")
fun ImageView.setWeatherIcon(url: String?) {
    when (url) {
        "01d" -> setImageResource(R.drawable.ic_clear_sky)
        "01n" -> setImageResource(R.drawable.ic_clear_sky)
        "02d" -> setImageResource(R.drawable.ic_few_clouds)
        "02n" -> setImageResource(R.drawable.ic_few_clouds)
        "03d" -> setImageResource(R.drawable.ic_scattered_clouds)
        "03n" -> setImageResource(R.drawable.ic_scattered_clouds)
        "04d" -> setImageResource(R.drawable.ic_broken_clouds)
        "04n" -> setImageResource(R.drawable.ic_broken_clouds)
        "09d" -> setImageResource(R.drawable.ic_shower_rain)
        "09n" -> setImageResource(R.drawable.ic_shower_rain)
        "10d" -> setImageResource(R.drawable.ic_rain)
        "10n" -> setImageResource(R.drawable.ic_rain)
        "11d" -> setImageResource(R.drawable.ic_thunderstorm)
        "11n" -> setImageResource(R.drawable.ic_thunderstorm)
        "13d" -> setImageResource(R.drawable.ic_snow)
        "13n" -> setImageResource(R.drawable.ic_snow)
        "50d" -> setImageResource(R.drawable.ic_mist)
        "50n" -> setImageResource(R.drawable.ic_mist)
        // weather icons : https://openweathermap.org/weather-conditions
        else -> setImageResource(R.drawable.ic_cloud_black_24dp)
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("bindDate")
fun TextView.bindDate(dt:Long) {
    text = dt.formatDate()
}






