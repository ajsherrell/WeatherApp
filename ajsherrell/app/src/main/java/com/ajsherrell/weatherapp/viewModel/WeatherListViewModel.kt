package com.ajsherrell.weatherapp.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.adapter.WeatherAdapter
import com.ajsherrell.weatherapp.base.BaseViewModel
import com.ajsherrell.weatherapp.model.*
import com.ajsherrell.weatherapp.network.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherListViewModel:BaseViewModel() {
    @Inject
    lateinit var weatherApi: WeatherApi

    private lateinit var subscription: Disposable

    private val TAG: String = "WeatherListViewModel"

    val errorMessage: MutableLiveData<Int?> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadWeather() }

    val loadingVisibility: MutableLiveData<Int?> = MutableLiveData()

    private val weatherAdapter: WeatherAdapter = WeatherAdapter()

    private lateinit var response: Response
    private lateinit var category: Category
    private lateinit var weather: Weather
    private lateinit var main: Main

    private val shortDescription = MutableLiveData<String?>()
    private val masterTemp = MutableLiveData<String?>()
    private val weatherIcon = MutableLiveData<String?>()
    private val minMaxTemp = MutableLiveData<String?>()
    private val day = MutableLiveData<String?>()

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
                { e ->
                    Log.e("Subscriber", "error fetching weather: ${e.message}")
                    e.printStackTrace()
                    onRetrieveWeatherListError() }
            )
    }

    private fun onRetrieveWeatherListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveWeatherListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveWeatherListSuccess(response: List<Response>){
        weatherAdapter.updateListItems(response)
    }

    private fun onRetrieveWeatherListError(){
        errorMessage.value = R.string.errorWeather
    }

    fun bind(response: Response) {
        this.response = response
        day.value = category.dt_txt
        weatherIcon.value = weather.icon
        minMaxTemp.value = main.getMinMaxTemp()
        Log.d(TAG, "day + minMaxTemp: " + day + minMaxTemp)
    }

    fun getMasterTemp():MutableLiveData<String?> {
        masterTemp.value = main.getTemp()
        return masterTemp
    }

    fun getMasterMinMaxTemp():MutableLiveData<String?> {
        minMaxTemp.value = main.getMinMaxTemp()
        return minMaxTemp
    }

    fun getMasterShortDescription():MutableLiveData<String?> {
        return shortDescription
    }

    fun getMasterWeatherIcon():MutableLiveData<String?> {
        return weatherIcon
    }

    fun getMasterDay():MutableLiveData<String?> {
        return day
    }

}


