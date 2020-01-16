package com.ajsherrell.weatherapp

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ajsherrell.weatherapp.databinding.WeatherDetailFragmentBinding
import com.ajsherrell.weatherapp.viewModel.WeatherDetailViewModel


class WeatherDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherDetailFragment()
    }

    private lateinit var mRootView: View
    private lateinit var mBinding: WeatherDetailFragmentBinding
    private lateinit var mViewModel: WeatherDetailViewModel

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        lifecycle.addObserver(mViewModel)
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        lifecycle.removeObserver(mViewModel)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = WeatherDetailFragmentBinding.inflate(inflater, container, false)
        mRootView = mBinding.root
        mBinding.lifecycleOwner = this

        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(WeatherDetailViewModel::class.java)
        mBinding.viewModel = mViewModel
    }

}
