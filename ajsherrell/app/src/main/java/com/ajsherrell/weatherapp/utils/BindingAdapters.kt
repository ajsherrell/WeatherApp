package com.ajsherrell.weatherapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.utils.extension.getParentActivity
import com.bumptech.glide.Glide
import java.util.Observer

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

    @BindingAdapter("mutableVisibility")
    @JvmStatic
    fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
        val parentActivity: AppCompatActivity? = view.getParentActivity()
        if(parentActivity != null && visibility != null) {
            visibility.observe(parentActivity, androidx.lifecycle.Observer { value -> view.visibility = value?:View.VISIBLE})
        }
    }

    @BindingAdapter("mutableText")
    @JvmStatic
    fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
        val parentActivity:AppCompatActivity? = view.getParentActivity()
        if(parentActivity != null && text != null) {
            text.observe(parentActivity, androidx.lifecycle.Observer { value -> view.text = value?:""})
        }
    }

    @BindingAdapter("adapter")
    @JvmStatic
    fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter
        view.setHasFixedSize(true)
    }
}



