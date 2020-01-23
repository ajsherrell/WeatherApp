package com.ajsherrell.weatherapp.base

import androidx.lifecycle.ViewModel
import com.ajsherrell.weatherapp.component.DaggerViewModelInjector
import com.ajsherrell.weatherapp.component.ViewModelInjector
import com.ajsherrell.weatherapp.network.NetworkModule
import com.ajsherrell.weatherapp.viewModel.DetailViewModel
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    //injects required dependencies
    private fun inject() {
        when (this) {
            is WeatherListViewModel -> injector.inject(this)
            is DetailViewModel -> injector.inject(this)
        }
    }

}