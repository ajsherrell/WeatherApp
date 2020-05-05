package com.ajsherrell.android.quiz_hyperbaric

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.ajsherrell.android.quiz_hyperbaric.adapter.QuizListAdapter
import com.ajsherrell.android.quiz_hyperbaric.adapter.QuizListClickListener
import com.ajsherrell.android.quiz_hyperbaric.databinding.FragmentListBinding
import com.ajsherrell.android.quiz_hyperbaric.viewModel.QuizListViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class ListFragment : Fragment() {

    private var errorSnackbar: Snackbar? = null

    private val model: QuizListViewModel by lazy {
        requireNotNull(activity).let {
            ViewModelProviders.of(
                this,
                QuizListViewModel.Factory(it.application)
            )[QuizListViewModel::class.java]
        }
    }


    private lateinit var binding: FragmentListBinding
    private lateinit var rootView: View

    private val quizListAdapter = QuizListAdapter(object : QuizListClickListener {
        override fun onItemClicked(position: Int) {
            Toast.makeText(context,"This item was clicked", Toast.LENGTH_SHORT).show()
            Timber.d("Position clicked: $position!!!")
            //todo: declare function to launch detail fragment with the position.
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        model.quizData.observe(viewLifecycleOwner, Observer {
            quizListAdapter.updateListItems(it.category)
            quizListAdapter.notifyDataSetChanged()
        })

        binding.listRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = quizListAdapter
        }
        rootView = binding.root

        Timber.d("JSON data!!! = ${model.quizData}")
        return rootView
    }

}