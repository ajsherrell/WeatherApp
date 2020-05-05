 package com.ajsherrell.android.quiz_hyperbaric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ajsherrell.android.quiz_hyperbaric.databinding.ActivityMainBinding
import timber.log.Timber

 class MainActivity : AppCompatActivity() {

    //data binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("MainActivity has started in onCreate!!!")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
