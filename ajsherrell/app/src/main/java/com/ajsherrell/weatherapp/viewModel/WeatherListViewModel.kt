package com.ajsherrell.weatherapp.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.adapter.ForecastClickListener
import com.ajsherrell.weatherapp.adapter.WeatherAdapter
import com.ajsherrell.weatherapp.base.BaseViewModel
import com.ajsherrell.weatherapp.model.Response
import com.ajsherrell.weatherapp.model.context
import com.ajsherrell.weatherapp.network.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class WeatherListViewModel:BaseViewModel() {
    @Inject
    lateinit var weatherApi: WeatherApi

    private lateinit var subscription: Disposable
    abstract val listener: ForecastClickListener
    val weatherAdapter: WeatherAdapter = WeatherAdapter(listener)

    private val TAG: String = "WeatherListViewModel"

    val errorMessage: MutableLiveData<Int?> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadWeather() }

    val loadingVisibility: MutableLiveData<Int?> = MutableLiveData()

    private var response: Response? = null

    private val _shortDescription: MutableLiveData<String?> = MutableLiveData()
    val shortDescription: LiveData<String?>
        get() = _shortDescription

    private val _masterTemp: MutableLiveData<String?> = MutableLiveData()
    val masterTemp: LiveData<String?>
        get() = _masterTemp

    private val _minTemp: MutableLiveData<String?> = MutableLiveData()
    val minTemp: LiveData<String?>
        get() = _minTemp

    private val _maxTemp: MutableLiveData<String?> = MutableLiveData()
    val maxTemp: LiveData<String?>
        get() = _maxTemp

    private val weatherIcon: MutableLiveData<String?> = MutableLiveData()
    val masterWeatherIcon :LiveData<String?>
        get() = weatherIcon

    private val _minMaxTemp: MutableLiveData<String?> = MutableLiveData()
    val minMaxTemp: LiveData<String?>
        get() = _minMaxTemp

    private val _day: MutableLiveData<String?> = MutableLiveData()
    val day: LiveData<String?>
        get() = _day

    private val _description: MutableLiveData<String?> = MutableLiveData()
    val description: LiveData<String?>
        get() = _description

    private val _humidity: MutableLiveData<String?> = MutableLiveData()
    val humidity: LiveData<String?>
        get() = _humidity

    private val _windSpeed: MutableLiveData<String?> = MutableLiveData()
    val windSpeed: LiveData<String?>
        get() = _windSpeed

    private val _weatherForecast: MutableLiveData<Response> = MutableLiveData()
    val weatherForecast :LiveData<Response>
        get() = _weatherForecast

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
                { result ->
                    _weatherForecast.value = result
                    _minMaxTemp.value = context?.resources?.getString(R.string.minMinTempString, result.category[0].main.temp_min, result.category[0].main.temp_max)
                    _shortDescription.value = result.category[0].weather[0].main
                    weatherIcon.value = result.category[0].weather[0].icon
                    Log.d(TAG, "loadWeather: $_shortDescription")
                    onRetrieveWeatherListSuccess(result)
                },
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

    private fun onRetrieveWeatherListSuccess(response: Response){
       weatherAdapter.updateListItems(response.category)
    }

    private fun onRetrieveWeatherListError(){
        errorMessage.value = R.string.errorWeather
    }
}


