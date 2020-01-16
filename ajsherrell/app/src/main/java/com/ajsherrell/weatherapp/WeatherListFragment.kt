package com.ajsherrell.weatherapp

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajsherrell.weatherapp.adapter.WeatherAdapter
import com.ajsherrell.weatherapp.databinding.WeatherListFragmentBinding
import com.ajsherrell.weatherapp.model.List
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel
import io.reactivex.disposables.CompositeDisposable


class WeatherListFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private val TAG: String = "MainActivity"
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mAdapter: RecyclerView.Adapter<*>

    private lateinit var mViewModel: WeatherListViewModel
    private val disposable = CompositeDisposable()
    private lateinit var rootView: View
    private lateinit var mBinding: WeatherListFragmentBinding

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        lifecycle.addObserver(viewModel)
//    }

//    override fun onDetach() {
//        super.onDetach()
//        lifecycle.removeObserver(viewModel)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = WeatherListFragmentBinding.inflate(inflater, container, false)
        rootView = mBinding.root
        mBinding.lifecycleOwner = this

        mLayoutManager = LinearLayoutManager(context)

        mRecyclerView = mBinding.recyclerListFiveDay.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = mLayoutManager
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(WeatherListViewModel::class.java)
        mBinding.viewModel = mViewModel
    }

    //todo: add clicklistener

}

