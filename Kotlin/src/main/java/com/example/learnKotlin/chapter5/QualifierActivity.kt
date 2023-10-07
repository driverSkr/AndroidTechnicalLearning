package com.example.learnKotlin.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R

//使用限定符展示符合条件的资源文件(即res目录下包名的-的后缀)
class QualifierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qualifier)
    }
}