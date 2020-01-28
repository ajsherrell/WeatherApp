package com.ajsherrell.weatherapp

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ajsherrell.weatherapp.databinding.WeatherDetailFragmentBinding
import com.ajsherrell.weatherapp.viewModel.DetailViewModel


class WeatherDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherDetailFragment()
    }

    private lateinit var mRootView: View
    private lateinit var mBinding: WeatherDetailFragmentBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = WeatherDetailFragmentBinding.inflate(inflater, container, false)

        mRootView = mBinding.root
        return mRootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = detailViewModel
    }

}
