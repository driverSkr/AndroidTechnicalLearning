package com.example.learnKotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.Kotlin.R
import com.example.learnKotlin.chapter4.AlertDialogActivity
import com.example.learnKotlin.chapter4.ChatInterfaceActivity
import com.example.learnKotlin.chapter5.DynamicAddActivity
import com.example.learnKotlin.chapter5.QualifierActivity
import com.example.learnKotlin.chapter5.XmlAddFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alert_dialog.setOnClickListener(this)
        chat_interface.setOnClickListener(this)
        xml_add_fragment.setOnClickListener(this)
        dynamic_add_fragment.setOnClickListener(this)
        qualifier.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.alert_dialog -> startActivity(Intent(this,AlertDialogActivity::class.java))
            R.id.chat_interface -> startActivity(Intent(this,ChatInterfaceActivity::class.java))
            R.id.xml_add_fragment -> startActivity(Intent(this,XmlAddFragmentActivity::class.java))
            R.id.dynamic_add_fragment -> startActivity(Intent(this,DynamicAddActivity::class.java))
            R.id.qualifier -> startActivity(Intent(this,QualifierActivity::class.java))
        }
    }
}