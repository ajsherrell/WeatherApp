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
import com.ajsherrell.weatherapp.model.Response
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.ViewHolder>(),
    iListener {

    private var responseList: Response? = null
    private lateinit var category: List<Category>

    private val weatherDetailFragment = WeatherDetailFragment()
    private val activity: MainActivity = MainActivity()

    class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = WeatherListViewModel()

        fun bind(category: Category, listener: View.OnClickListener) {
            viewModel.bind(category)
            binding.recyclerClickListener = listener
            binding.viewModel = viewModel
            binding.executePendingBindings()
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
        return if(::category.isInitialized) category.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category[position], onItemClick(position))

    }

    fun updateListItems(categoryList: List<Category>) {
        this.category = categoryList
        notifyDataSetChanged()
    }
}



