package com.example.learnKotlin.chapter13.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learnKotlin.chapter13.viewModel.CounterViewModel

class CounterViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CounterViewModel(countReserved) as T
    }
}