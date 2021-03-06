package com.ajsherrell.weatherapp.network

import com.ajsherrell.weatherapp.model.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface WeatherApi {

    @GET("forecast?zip=30338&appid=8eecd54126fb52847b1065881d17dc6f&units=imperial")
    fun getWeather(): Observable<Response>
}

