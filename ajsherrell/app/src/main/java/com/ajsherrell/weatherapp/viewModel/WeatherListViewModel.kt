package com.ajsherrell.weatherapp.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import com.ajsherrell.weatherapp.model.List
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather

class WeatherListViewModel : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    private var mList: List? = null
    private var mWeather: Weather? = null
    private var mMain: Main? = null

    var list: List?
    @Bindable get() = mList
    set(value) {
        mList = value
        callbacks.notifyChange(this, BR.viewModel)
    }

    var weather: Weather?
    @Bindable get() = mWeather
    set(value) {
        mWeather = value
        callbacks.notifyChange(this, BR.viewModel)
    }

    var main: Main?
    @Bindable get() = mMain
    set(value) {
        mMain = value
        callbacks.notifyChange(this, BR.viewModel)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }
}
