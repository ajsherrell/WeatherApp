package com.ajsherrell.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajsherrell.weatherapp.databinding.RecyclerItemBinding
import com.ajsherrell.weatherapp.model.Category

class ViewHolder(
    private val binding: RecyclerItemBinding,
    private val clickListener: ForecastClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category, position: Int) {
        binding.executePendingBindings()
        binding.forecast = category
        binding.position = position
        binding.onClickRvItem  = clickListener
    }
}


class WeatherAdapter (val clickListener: ForecastClickListener): RecyclerView.Adapter<ViewHolder>() {

    private var category: List<Category> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category[position], position)

    }

    fun updateListItems(categoryList: List<Category>) {
        this.category = categoryList
        notifyDataSetChanged()
    }
}

interface ForecastClickListener {
    fun onItemClicked(position: Int)
}


