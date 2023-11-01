package com.example.jetpack.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class StateFlowBasicViewModel: ViewModel() {

    private val _stateFlow = MutableStateFlow(0)

    val stateFlow = _stateFlow.asStateFlow()

    fun startTimer() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                _stateFlow.value += 1
            }
        },0,1000)
    }
}