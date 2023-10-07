package com.example.learnKotlin.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Kotlin.R

//XML文件中静态的引入Fragment
class XmlAddFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_add_fragment)
    }
}