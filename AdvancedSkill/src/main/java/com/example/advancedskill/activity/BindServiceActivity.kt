package com.example.advancedskill.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.advancedskill.databinding.ActivityBindServiceBinding
import com.example.advancedskill.service.BindService

class BindServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBindServiceBinding

    private var myBinder: BindService.Companion.MyBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBindServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bindServiceBtn.setOnClickListener {
            val intent = Intent(this, BindService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        binding.unbindServiceBtn.setOnClickListener {
            myBinder?.endBind()
            unbindService(connection)
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myBinder = service as BindService.Companion.MyBinder
            myBinder?.startBind()
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }
}