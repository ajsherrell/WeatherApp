package com.ajsherrell.weatherapp.network

import android.provider.SyncStateContract
import com.ajsherrell.weatherapp.utils.BASE_URL
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideApiService(moshi: Moshi) {

//    val retrofit = Retrofit.Builder() //todo: not sure what else I need here. Also how do I add my api key?
//        .baseUrl(BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .build()
}

//retrofit example
//val retrofit = Retrofit.Builder()
//    .baseUrl("http://www.myexampleurl.com")
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//
//val api = retrofit.create<BooksApi>(BooksApi::class.java)

//Observer pattern example
//apiService.getData(someData)
//.subscribeOn(Schedulers.io())
//.observeOn(AndroidSchedulers.mainThread())
//.subscribe (/* an Observer */)


