package com.ajsherrell.weatherapp.component

import com.ajsherrell.weatherapp.network.NetworkModule
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(weatherListViewModel: WeatherListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}