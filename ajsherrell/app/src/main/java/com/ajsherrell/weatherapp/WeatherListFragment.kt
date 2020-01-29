package com.ajsherrell.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajsherrell.weatherapp.adapter.ForecastClickListener
import com.ajsherrell.weatherapp.adapter.WeatherAdapter
import com.ajsherrell.weatherapp.databinding.WeatherListFragmentBinding
import com.ajsherrell.weatherapp.model.Response
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherListFragment : Fragment() {

    private var errorSnackbar: Snackbar? = null

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private val TAG: String = "WeatherListFragment"

    private val weatherListViewModel: WeatherListViewModel by activityViewModels()

    private lateinit var rootView: View
    private lateinit var binding: WeatherListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherListFragmentBinding.inflate(inflater, container, false)

//        weatherListViewModel = ViewModelProvider(this)[WeatherListViewModel::class.java]

        rootView = binding.root
        binding.seeDetailsButton.setOnClickListener {
            launchDetailFragment()
        }

        binding.recyclerListFiveDay.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        weatherListViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.lifecycleOwner = this
        binding.viewModel = weatherListViewModel


        val adapter = WeatherAdapter(object : ForecastClickListener {
            override fun onItemClicked(position: Int) {
                // Will be launching the other fragment with this position
                launchDetailFragment(position)
                Log.d(javaClass.simpleName, "clicked on item $position")
            }
        })

        weatherListViewModel.weatherForecast.observe(viewLifecycleOwner, Observer<Response> {
            adapter.updateListItems(it.category)
            adapter.notifyDataSetChanged()
        })

        binding.recyclerListFiveDay.adapter = adapter
        binding.recyclerListFiveDay.setHasFixedSize(true)


        return rootView
    }
    private fun launchDetailFragment(pos: Int  = 0 ) {
        val action =
            WeatherListFragmentDirections.actionWeatherListFragmentToWeatherDetailFragment(
                pos
            )

        findNavController().navigate(action)
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, weatherListViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}

