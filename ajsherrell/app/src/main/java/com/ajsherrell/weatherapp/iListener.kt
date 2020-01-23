package com.ajsherrell.weatherapp

import android.content.Context
import android.view.View

interface iListener {
    fun onItemClick(): View.OnClickListener
}