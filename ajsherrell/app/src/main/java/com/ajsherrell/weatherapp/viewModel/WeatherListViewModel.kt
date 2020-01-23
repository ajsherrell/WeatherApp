package com.ajsherrell.weatherapp.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.adapter.WeatherAdapter
import com.ajsherrell.weatherapp.base.BaseViewModel
import com.ajsherrell.weatherapp.model.Category
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather
import com.ajsherrell.weatherapp.network.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherListViewModel:BaseViewModel() {
    @Inject
    lateinit var weatherApi: WeatherApi

    private lateinit var subscription: Disposable

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadWeather() }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val weatherAdapter: WeatherAdapter = WeatherAdapter()

    private val shortDescription = MutableLiveData<String>()
    private val masterTemp = MutableLiveData<String>()
    private val weatherIcon = MutableLiveData<String>()
    private val minMaxTemp = MutableLiveData<String>()
    private val day = MutableLiveData<String>()

    init {
        loadWeather()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadWeather() {
        subscription = weatherApi.getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveWeatherListStart() }
            .doOnTerminate { onRetrieveWeatherListFinish() }
            .subscribe(
                { result -> onRetrieveWeatherListSuccess(result) },
                { onRetrieveWeatherListError() }
            )
    }

    private fun onRetrieveWeatherListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveWeatherListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveWeatherListSuccess(categoryList:List<Category>){
        weatherAdapter.updateListItems(categoryList)
    }

    private fun onRetrieveWeatherListError(){
        errorMessage.value = R.string.errorWeather
    }

    fun bindWeather(weather: Weather) {
        shortDescription.value = weather.main
        weatherIcon.value = weather.icon
    }

    fun getWeatherListShortDescription() = shortDescription

    fun getWeatherListIcon() = weatherIcon

    fun bindMain(main: Main) {
        masterTemp.value = main.getTemp()
        minMaxTemp.value = main.getMinMaxTemp()
    }

    fun getWeatherListTemp() = masterTemp

    fun getWeatherListMinMaxTemp() = minMaxTemp

    fun bindCategory(category: Category) {
        day.value = category.dt_txt
    }

    fun getWeatherListDay() = day
}


