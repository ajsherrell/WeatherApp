package com.ajsherrell.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ajsherrell.weatherapp.MainActivity
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.WeatherDetailFragment
import com.ajsherrell.weatherapp.databinding.RecyclerItemBinding
import com.ajsherrell.weatherapp.model.List
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather
import kotlinx.android.synthetic.main.recycler_item.view.*

class WeatherAdapter(val weather: Array<Weather>,
                     val main: Array<Main>,
                     val list: Array<List>) :
RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val weatherDetailFragment = WeatherDetailFragment()
    private val activity: MainActivity = MainActivity()

    class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var mlistDayTextView: TextView? = null
        private var mRvWeatherImageView: ImageView? = null
        private var mlistMinMaxTextView: TextView? = null
        private var mClicklistener: View.OnClickListener? = null

        init {
            mlistDayTextView = binding.listDayTextView
            mRvWeatherImageView = binding.rvWeatherIcon
            mlistMinMaxTextView = binding.listMinMaxTextView
            mClicklistener = binding.clickListener
        }

        fun bind(list: List, main: Main, weather: Weather, listener: View.OnClickListener) {
            mlistDayTextView?.text = list.dt_txt
            mlistMinMaxTextView?.text = main.getMinMaxTemp().toString()
            mRvWeatherImageView?.setImageResource(weather.icon)
            mClicklistener?.onClick(listener)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            openDetailFragment()
        }
    }

    private fun openDetailFragment() {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.root_layout, weatherDetailFragment)
        fragmentTransaction.commit()
    }

    override fun getItemCount(): Int {
        //todo: fix
        return
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mList: List = list[position]
        val mWeather: Weather = weather[position]
        val mMain: Main = main[position]
        holder.bind(mList, mMain, mWeather, createOnClickListener())
    }

}


