package com.ajsherrell.weatherapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ajsherrell.weatherapp.model.Weather
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("imageRes")
    @JvmStatic
    fun setIconImage(imageView: ImageView, url: String?) {
        if (url == null) {
            imageView.setImageResource(R.drawable.ic_cloud_black_24dp)
        } else {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }

    @BindingAdapter("imageRes")
    @JvmStatic
    fun setIconImage(imageView: ImageView, url: Int?) {
        if (url == null) {
            imageView.setImageResource(R.drawable.ic_cloud_black_24dp)
        } else {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }

}

