package com.ajsherrell.android.quiz_hyperbaric.network

import com.ajsherrell.android.quiz_hyperbaric.model.Response
import retrofit2.http.GET

interface QuizApi {

    @GET("/")
    suspend fun getQuiz(): Response

}