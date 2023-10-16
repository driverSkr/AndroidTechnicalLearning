package com.example.learnKotlin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.example.Kotlin.R
import com.example.learnKotlin.chapter12.ToolbarActivity
import com.example.learnKotlin.chapter13.CounterActivity
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
import com.example.learnKotlin.chapter7.*
import com.example.learnKotlin.chapter8.ReadSystemContactActivity
import com.example.learnKotlin.chapter8.RuntimePermissionsActivity
import com.example.learnKotlin.chapter9.CallCameraPhotosActivity
import com.example.learnKotlin.chapter9.MediaPlayerActivity
import com.example.learnKotlin.chapter9.VideoViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
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
        file_storage.setOnClickListener(this)
        shared_preferences.setOnClickListener(this)
        sqlite_open_helper.setOnClickListener(this)
        standard_helper.setOnClickListener(this)
        room.setOnClickListener(this)
        runtime_permission.setOnClickListener(this)
        read_system_contact.setOnClickListener(this)
        call_camera_photos.setOnClickListener(this)
        media_player.setOnClickListener(this)
        video_view.setOnClickListener(this)
        toolbar.setOnClickListener(this)
        counter.setOnClickListener(this)
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
            R.id.file_storage -> startActivity(Intent(this,FileStorageReadActivity::class.java))
            R.id.shared_preferences -> startActivity(Intent(this,SharedPreferencesStorageReadActivity::class.java))
            R.id.sqlite_open_helper -> startActivity(Intent(this, SQLiteOpenHelperActivity::class.java))
            R.id.standard_helper -> startActivity(Intent(this, StandardDatabaseHelperActivity::class.java))
            R.id.room -> startActivity(Intent(this, RoomActivity::class.java))
            R.id.runtime_permission -> startActivity(Intent(this, RuntimePermissionsActivity::class.java))
            R.id.read_system_contact -> startActivity(Intent(this, ReadSystemContactActivity::class.java))
            R.id.call_camera_photos -> startActivity(Intent(this, CallCameraPhotosActivity::class.java))
            R.id.media_player -> startActivity(Intent(this, MediaPlayerActivity::class.java))
            R.id.video_view -> startActivity(Intent(this, VideoViewActivity::class.java))
            R.id.toolbar -> startActivity(Intent(this,ToolbarActivity::class.java))
            R.id.counter -> startActivity(Intent(this,CounterActivity::class.java))
        }
    }
}