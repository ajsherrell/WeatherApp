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
import com.ajsherrell.weatherapp.model.Category
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Response
import com.ajsherrell.weatherapp.model.Weather
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.ViewHolder>(),
    iListener {

    private lateinit var responseList: List<Response>

    private lateinit var weatherList:List<Weather>
    private lateinit var categoryList:List<Category>
    private lateinit var mainList:List<Main>

    private val weatherDetailFragment = WeatherDetailFragment()
    private val activity: MainActivity = MainActivity()

    class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = WeatherListViewModel()

        fun bind(response: Response, listener: View.OnClickListener) {
            viewModel.bind(response)
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
        return if(::responseList.isInitialized) responseList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(responseList[position], onItemClick(position))

    }

    fun updateListItems(response: List<Response>) {
        this.responseList = response
        notifyDataSetChanged()
    }
}



