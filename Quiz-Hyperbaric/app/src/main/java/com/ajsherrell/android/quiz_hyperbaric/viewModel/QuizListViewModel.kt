package com.ajsherrell.android.quiz_hyperbaric.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.ajsherrell.android.quiz_hyperbaric.QuizApplication
import com.ajsherrell.android.quiz_hyperbaric.database.QuizRepository
import com.ajsherrell.android.quiz_hyperbaric.model.Category
import com.ajsherrell.android.quiz_hyperbaric.model.Response
import com.ajsherrell.android.quiz_hyperbaric.network.NetworkModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import java.lang.IllegalArgumentException


class QuizListViewModel(application: Application): AndroidViewModel(application) {
    private val repository = QuizRepository()

    private val job = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _refreshing: MutableLiveData<Boolean> = MutableLiveData(false)
    val refreshing :LiveData<Boolean>
        get() = _refreshing

    private val _quizData: MutableLiveData<Response> = MutableLiveData()
    val quizData: LiveData<Response>
        get() = _quizData

    private val _category: MutableLiveData<List<Category>> = MutableLiveData()
    val category: LiveData<List<Category>>
        get() = _category

    init {
        refreshDataFromRepo()
    }

    private fun refreshDataFromRepo() {
        coroutineScope.launch {
            try {
                _refreshing.value = true
                repository.getResponse()
                Timber.d("Data refreshed from repository!!! ${repository.getResponse()}")
            } catch (e: IOException) {
                "error fetching Quiz Api: ${e.message}".apply {
                    _errorMessage.value = this
                    Timber.e(this)
                }
                e.printStackTrace()
            } finally {
                _refreshing.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class Factory(private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuizListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuizListViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
}