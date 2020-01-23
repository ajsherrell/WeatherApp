package com.ajsherrell.weatherapp.network

import com.ajsherrell.weatherapp.model.*
import io.reactivex.Observable
import retrofit2.http.GET

interface WeatherApi {


    @GET("forecast?zip=30338&appid=8eecd54126fb52847b1065881d17dc6f")
    fun getWeather(): Observable<List<Category>>
//    @GET("/list/weather")
//    fun listWeather(): Observable<List<Weather>>
//
//    @GET("/list/main")
//    fun listMain(): Observable<List<Main>>
//
//    @GET("/list")
//    fun listCategory(): Observable<List<Category>>
//
//    @GET("/list/wind")
//    fun listWind(): Observable<List<Wind>>

}

