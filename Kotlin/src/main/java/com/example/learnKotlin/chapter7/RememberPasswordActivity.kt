package com.example.learnKotlin.chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R

//使用SharedPreferences实现记住密码功能
class RememberPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remember_password)
    }
}