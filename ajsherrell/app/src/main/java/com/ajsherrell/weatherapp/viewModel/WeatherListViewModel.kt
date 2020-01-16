package com.ajsherrell.weatherapp.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.ajsherrell.weatherapp.model.List
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather

class WeatherListViewModel : ViewModel(), LifecycleObserver {

    val mMain = ObservableField<Main>()

    val mWeather = ObservableField<Weather>()

    val mList = ObservableField<List>()

    val temp = mMain.set()


//    val main = ObservableField<Main>() //short description
//
//    val icon = ObservableField<Weather>() //weather icon
//
//    val temp = ObservableField<Main>()
//
//    val temp_min = ObservableField<Main>()
//
//    val temp_max = ObservableField<Main>()
//
//    val dt_txt = ObservableField<List>() // date that needs formatting
//
//    //todo: setters?
//    val minMaxTemp =

}

private fun <T> ObservableField<T>.set() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
