package com.ajsherrell.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ajsherrell.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //data binding
    private lateinit var binding: ActivityMainBinding

    private val fragmentManager = supportFragmentManager
    private val weatherListFragment = WeatherListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if(savedInstanceState == null) {
            //display weather list fragment initially
            init()
        }
    }

    private fun init() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.root_layout, weatherListFragment)
        fragmentTransaction.commit()
    }
}
