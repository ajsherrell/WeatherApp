package com.ajsherrell.weatherapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ajsherrell.weatherapp.model.Weather
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("masterWeatherIcon")
    @JvmStatic
    fun setMasterWeatherIconImage(imageView: ImageView, url: Weather?) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    @BindingAdapter("detailWeatherIcon")
    @JvmStatic
    fun setDetailWeatherIconImage(imageView: ImageView, url: Weather?) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    @BindingAdapter("rvWeatherIcon")
    @JvmStatic
    fun setRvWeatherIconImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

//    @BindingAdapter("umbrellaImageView")
//    fun setUmbrellaImage(imageView: ImageView, url: String?) {
//        Glide.with(imageView.context)
//            .load(url)
//            .into(imageView)
//    }

}

