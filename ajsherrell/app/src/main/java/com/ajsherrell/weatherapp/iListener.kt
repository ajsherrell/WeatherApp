package com.ajsherrell.weatherapp

import android.view.View

interface iListener {
    fun onItemClick(position: Int): View.OnClickListener
}