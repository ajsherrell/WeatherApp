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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ajsherrell.weatherapp.adapter.WeatherAdapter
import com.ajsherrell.weatherapp.adapter.iListener
import com.ajsherrell.weatherapp.databinding.WeatherListFragmentBinding
import com.ajsherrell.weatherapp.model.List
import com.ajsherrell.weatherapp.model.Main
import com.ajsherrell.weatherapp.model.Weather
import com.ajsherrell.weatherapp.viewModel.WeatherListViewModel
import io.reactivex.disposables.CompositeDisposable


class WeatherListFragment : Fragment(), iListener {

    private val weatherDetailFragment = WeatherDetailFragment()
    private val activity: MainActivity = MainActivity()

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private lateinit var mList: List
    private lateinit var mWeather: Weather
    private lateinit var mMain: Main

    private val TAG: String = "MainActivity"
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mAdapter: WeatherAdapter

    private lateinit var mViewModel: WeatherListViewModel
    private val disposable = CompositeDisposable() //todo: what is this for?
    private lateinit var rootView: View
    private lateinit var mBinding: WeatherListFragmentBinding
    private var mClicklistener: View.OnClickListener? = null

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
       // mBinding.swipeRefreshLayout.setOnRefreshListener(this)


        rootView = mBinding.root
        mBinding.lifecycleOwner = this

        mBinding.clickListener = mClicklistener

        mLayoutManager = LinearLayoutManager(context)

        mRecyclerView = mBinding.recyclerListFiveDay.apply {
            setHasFixedSize(true)
            if (::mAdapter.isInitialized) {
                adapter = mAdapter
            }
            layoutManager = mLayoutManager
        }


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(WeatherListViewModel::class.java)
        mBinding.viewModel = mViewModel
    }

    override fun onItemClick(): View.OnClickListener {
        return View.OnClickListener {
            openDetailFragment()
        }
    }

    private fun openDetailFragment() {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.root_layout, weatherDetailFragment)
        fragmentTransaction.addToBackStack(null).commit()
    }

//    override fun onRefresh() {//todo: for onSwipeRefresh...do I need?
//        onItemsLoadComplete()
//    }

//    private fun onItemsLoadComplete() {
//        mBinding.recyclerListFiveDay.adapter?.notifyDataSetChanged()
//        mBinding.swipeRefreshLayout.isRefreshing
//    }

}

