package com.ajsherrell.android.quiz_hyperbaric.network

import com.ajsherrell.android.quiz_hyperbaric.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object NetworkModule {
    val apiService: QuizApi by lazy {
        retrofit.create(QuizApi::class.java)
    }
}