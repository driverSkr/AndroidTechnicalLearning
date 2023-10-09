package com.example.learnKotlin.chapter7

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_shared_preferences_storage_read.*

//共享参数的存储和读取
class SharedPreferencesStorageReadActivity : AppCompatActivity() {

    private val TAG = "SharedPreferencesStorageReadActivity"

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences_storage_read)

        saveButton.setOnClickListener{
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("name","Tom")
            editor.putInt("age", 28)
            editor.putBoolean("married", false)
            editor.apply()
        }

        restoreButton.setOnClickListener{
            val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
            val name = prefs.getString("name","")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d(TAG, "name is $name")
            Log.d(TAG, "age is $age")
            Log.d(TAG, "married is $married")
        }
    }
}