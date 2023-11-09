package com.example.advancedskill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.advancedskill.activity.IntentParcelableActivity
import com.example.advancedskill.activity.IntentSerializableActivity
import com.example.advancedskill.activity.LikeWechatMiniProgramsActivity
import com.example.advancedskill.activity.RuntimePermissionActivity
import com.example.advancedskill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMainBinding? = null

    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.intentParcelable.setOnClickListener(this)
        binding.intentSerializable.setOnClickListener(this)
        binding.runtimePermission.setOnClickListener(this)
        binding.likeWechatMiniPrograms.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.intent_parcelable -> startActivity(Intent(this,IntentParcelableActivity::class.java))
            R.id.intent_serializable -> startActivity(Intent(this,IntentSerializableActivity::class.java))
            R.id.runtime_permission -> startActivity(Intent(this,RuntimePermissionActivity::class.java))
            R.id.like_wechat_mini_programs -> startActivity(Intent(this,LikeWechatMiniProgramsActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}