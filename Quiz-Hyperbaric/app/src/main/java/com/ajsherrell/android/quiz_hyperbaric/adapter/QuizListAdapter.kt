package com.ajsherrell.android.quiz_hyperbaric.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajsherrell.android.quiz_hyperbaric.QuizApplication
import com.ajsherrell.android.quiz_hyperbaric.R
import com.ajsherrell.android.quiz_hyperbaric.databinding.ButtonListBinding
import com.ajsherrell.android.quiz_hyperbaric.model.Category
import com.ajsherrell.android.quiz_hyperbaric.model.Response
import com.ajsherrell.android.quiz_hyperbaric.viewModel.QuizListViewModel

class QuizListAdapter (val clickListener: QuizListClickListener)
    : RecyclerView.Adapter<QuizListAdapter.QuizHolder>() {

    private var category: List<Category> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ButtonListBinding>(
            layoutInflater,
            R.layout.button_list,
            parent,
            false
        )

        return QuizHolder(binding, clickListener)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: QuizHolder, position: Int) {
        holder.bind(category[position], position)
    }

    fun updateListItems(categoryList: List<Category>) {
        this.category = categoryList
    }

    inner class QuizHolder(
        private val binding: ButtonListBinding,
        private val clickListener: QuizListClickListener) :
            RecyclerView.ViewHolder(binding.root) {

        init {
            binding.model = QuizListViewModel(QuizApplication())
        }

        fun bind(categoryList: Category, positionList: Int) {
            binding.apply {
                category = categoryList
                position = positionList
                onRecyclerViewItemClick = clickListener
                executePendingBindings()
            }
        }
    }

}

interface QuizListClickListener {
    fun onItemClicked(position: Int)
}