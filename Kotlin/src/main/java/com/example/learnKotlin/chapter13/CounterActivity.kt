package com.example.learnKotlin.chapter13

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.Kotlin.R
import com.example.learnKotlin.chapter13.util.CounterViewModelFactory
import com.example.learnKotlin.chapter13.viewModel.CounterViewModel
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {

    lateinit var viewModel: CounterViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved",0)
        viewModel = ViewModelProvider(this,CounterViewModelFactory(countReserved)).get(CounterViewModel::class.java)
        plusOneBtn.setOnClickListener {
            viewModel.counter ++
            refreshCounter()
        }
        clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
        refreshCounter()
    }

    override fun onPause() {
        super.onPause()
        sp.edit{
            putInt("count_reserved", viewModel.counter)
        }
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }
}