package com.ajsherrell.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajsherrell.weatherapp.MainActivity
import com.ajsherrell.weatherapp.R
import com.ajsherrell.weatherapp.WeatherDetailFragment
import com.ajsherrell.weatherapp.databinding.RecyclerItemBinding
import com.ajsherrell.weatherapp.iListener
import com.ajsherrell.weatherapp.model.Model
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.ViewHolder>(),
    iListener {

    private lateinit var category: Model.Category

    private lateinit var weatherList:List<Model.Weather>
    private lateinit var categoryList:List<Model.Category>
    private lateinit var mainList:List<Model.Main>

    private val weatherDetailFragment = WeatherDetailFragment()
    private val activity: MainActivity = MainActivity()

    class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = WeatherListViewModel()

        fun bind(category: Model.Category, main: Model.Main, weather: Model.Weather, listener: View.OnClickListener) {
            viewModel.bindCategory(category)
            viewModel.bindMain(main)
            viewModel.bindWeather(weather)
            binding.recyclerClickListener = listener
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onItemClick(position: Int): View.OnClickListener {
        return View.OnClickListener {
            openDetailFragment()
        }
    }

    private fun openDetailFragment() {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.root_layout, weatherDetailFragment)
        fragmentTransaction.addToBackStack(null).commit()
    }

    override fun getItemCount(): Int {
        var list = 0
        if(::categoryList.isInitialized && ::weatherList.isInitialized && ::mainList.isInitialized) {
            list = categoryList.size + weatherList.size + mainList.size
        }
            return list
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position], mainList[position], weatherList[position], onItemClick(position))

    }

    fun updateListItems(categoryList: Model.Category) {
        this.category = categoryList
        notifyDataSetChanged()
    }
}



