package com.ajsherrell.weatherapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajsherrell.weatherapp.databinding.WeatherListFragmentBinding
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherListFragment : Fragment(), iListener {

    private val weatherDetailFragment = WeatherDetailFragment()
    private var errorSnackbar: Snackbar? = null

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private val TAG: String = "MainActivity"

    private lateinit var weatherListViewModel: WeatherListViewModel
    private lateinit var rootView: View
    private lateinit var binding: WeatherListFragmentBinding
    private var mClicklistener: View.OnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherListFragmentBinding.inflate(inflater, container, false)

        rootView = binding.root

        binding.clickListener = mClicklistener
        binding.recyclerListFiveDay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherListViewModel = ViewModelProviders.of(this).get(WeatherListViewModel::class.java)

        //error snackbar
        weatherListViewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = weatherListViewModel
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, weatherListViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onItemClick(): View.OnClickListener {
        return View.OnClickListener {
            openDetailFragment()
        }
    }

    private fun openDetailFragment() {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.root_layout, weatherDetailFragment)
        fragmentTransaction?.addToBackStack(null)?.commit()
    }


}

