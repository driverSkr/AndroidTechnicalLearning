package com.example.learnKotlin.chapter13.viewModel

import androidx.lifecycle.ViewModel

class CounterViewModel(countReserved: Int): ViewModel() {

    var counter = countReserved
}