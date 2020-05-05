package com.ajsherrell.android.quiz_hyperbaric.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    val category: List<Category> = mutableListOf()
)

@JsonClass(generateAdapter = true)
data class Category(
    val title: String,
    val questions: List<Questions> = mutableListOf()
)

@JsonClass(generateAdapter = true)
data class Questions(
    val id: Int,
    val answer: String,
    val options: List<Options> = mutableListOf(),
    val questionText: String
)

@JsonClass(generateAdapter = true)
data class Options(
    val options: List<String>
)