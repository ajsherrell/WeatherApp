package com.ajsherrell.android.quiz_hyperbaric.model

data class Response(
    val category: List<Category> = mutableListOf()
)

data class Category(
    val title: String,
    val questions: List<Questions> = mutableListOf()
)

data class Questions(
    val id: Int,
    val answer: String,
    val options: List<Options> = mutableListOf(),
    val questionText: String
)

data class Options(
    val options: List<String>
)