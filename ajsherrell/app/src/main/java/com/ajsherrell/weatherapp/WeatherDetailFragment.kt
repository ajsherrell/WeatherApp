package com.ajsherrell.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ajsherrell.weatherapp.databinding.WeatherDetailFragmentBinding
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel


class WeatherDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherDetailFragment()
    }

    private val model: WeatherListViewModel by activityViewModels()

    private lateinit var mRootView: View
    private lateinit var mBinding: WeatherDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = WeatherDetailFragmentBinding.inflate(inflater, container, false)

        mRootView = mBinding.root
        mBinding.lifecycleOwner = this
        mBinding.viewModel = model
        return mRootView
    }
}
