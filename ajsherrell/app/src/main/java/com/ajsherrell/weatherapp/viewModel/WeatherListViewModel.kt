package com.ajsherrell.weatherapp.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.base.BaseViewModel
import com.ajsherrell.weatherapp.model.Category
import com.ajsherrell.weatherapp.model.Response
import com.ajsherrell.weatherapp.network.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherListViewModel : BaseViewModel() {
    @Inject
    lateinit var weatherApi: WeatherApi

    private lateinit var subscription: Disposable

    private val TAG: String = "WeatherListViewModel"

    val errorMessage: MutableLiveData<Int?> = MutableLiveData()

    val errorClickListener = View.OnClickListener { loadWeather() }

    val loadingVisibility: MutableLiveData<Int?> = MutableLiveData()

    private val _weatherForecast: MutableLiveData<Response> = MutableLiveData()
    val weatherForecast: LiveData<Response>
        get() = _weatherForecast

    private val _category: MutableLiveData<Category> = MutableLiveData()
    val category: LiveData<Category>
        get() = _category

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
                    _category.value = result.category.first()
                },
                { e ->
                    Log.e("Subscriber", "error fetching weather: ${e.message}")
                    e.printStackTrace()
                    onRetrieveWeatherListError()
                }
            )
    }

    private fun onRetrieveWeatherListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveWeatherListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveWeatherListError() {
        errorMessage.value = R.string.errorWeather
    }
}


