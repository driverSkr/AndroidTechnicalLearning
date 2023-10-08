package com.example.learnKotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.Kotlin.R
import com.example.learnKotlin.chapter4.AlertDialogActivity
import com.example.learnKotlin.chapter4.ChatInterfaceActivity
import com.example.learnKotlin.chapter5.DynamicAddActivity
import com.example.learnKotlin.chapter5.NewsMainActivity
import com.example.learnKotlin.chapter5.QualifierActivity
import com.example.learnKotlin.chapter5.XmlAddFragmentActivity
import com.example.learnKotlin.chapter6.LoginActivity
import com.example.learnKotlin.chapter6.SendOrderBroadcastActivity
import com.example.learnKotlin.chapter6.SendStandardBroadcastActivity
import com.example.learnKotlin.chapter6.TimeChangeActivity
import com.example.learnKotlin.chapter6.broadcast.BootCompleteReceiver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alert_dialog.setOnClickListener(this)
        chat_interface.setOnClickListener(this)
        xml_add_fragment.setOnClickListener(this)
        dynamic_add_fragment.setOnClickListener(this)
        qualifier.setOnClickListener(this)
        simple_news.setOnClickListener(this)
        time_change.setOnClickListener(this)
        boot_complete.setOnClickListener(this)
        send_order.setOnClickListener(this)
        send_standard.setOnClickListener(this)
        login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.alert_dialog -> startActivity(Intent(this,AlertDialogActivity::class.java))
            R.id.chat_interface -> startActivity(Intent(this,ChatInterfaceActivity::class.java))
            R.id.xml_add_fragment -> startActivity(Intent(this,XmlAddFragmentActivity::class.java))
            R.id.dynamic_add_fragment -> startActivity(Intent(this,DynamicAddActivity::class.java))
            R.id.qualifier -> startActivity(Intent(this,QualifierActivity::class.java))
            R.id.simple_news -> startActivity(Intent(this,NewsMainActivity::class.java))
            R.id.time_change -> startActivity(Intent(this,TimeChangeActivity::class.java))
            R.id.boot_complete -> startActivity(Intent(this,BootCompleteReceiver::class.java))
            R.id.send_standard -> startActivity(Intent(this,SendStandardBroadcastActivity::class.java))
            R.id.send_order -> startActivity(Intent(this,SendOrderBroadcastActivity::class.java))
            R.id.login -> startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}