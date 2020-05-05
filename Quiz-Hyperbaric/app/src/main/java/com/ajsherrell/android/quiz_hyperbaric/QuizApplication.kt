package com.ajsherrell.android.quiz_hyperbaric

import android.app.Application
import com.ajsherrell.android.quiz_hyperbaric.database.QuizRepository
import timber.log.Timber

class QuizApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }

}