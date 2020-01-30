package com.ajsherrell.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ajsherrell.weatherapp.databinding.WeatherDetailFragmentBinding
import com.ajsherrell.weatherapp.model.Response
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel


class WeatherDetailFragment : Fragment() {

    private val TAG = "WeatherDetailFragment"

    companion object {
        fun newInstance() = WeatherDetailFragment()
    }

    private val model: WeatherListViewModel by activityViewModels()

    private lateinit var mRootView: View
    private lateinit var mBinding: WeatherDetailFragmentBinding

    private val args: WeatherDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = WeatherDetailFragmentBinding.inflate(inflater, container, false)
        val index = args.position
        Log.d(TAG, "position =  $index")

        model.weatherForecast.observe(viewLifecycleOwner,
            Observer<Response> {
                it?.let {
                    mBinding.category =  it.category[index]
                    Log.d(TAG, "position =  $index\ncategory ${it.category[index]}")
                }
            })

        mRootView = mBinding.root
        mBinding.lifecycleOwner = this
        return mRootView
}
}
