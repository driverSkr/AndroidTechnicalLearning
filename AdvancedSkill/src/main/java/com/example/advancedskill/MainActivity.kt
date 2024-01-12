package com.example.advancedskill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.advancedskill.activity.*
import com.example.advancedskill.databinding.ActivityMainBinding
import com.example.advancedskill.extension.navigateTo
import com.example.advancedskill.extension.setOnClickListener
import com.example.advancedskill.extension.toActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMainBinding? = null

    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //普通写法
        /*binding.intentParcelable.setOnClickListener(this)
        binding.intentSerializable.setOnClickListener(this)
        binding.runtimePermission.setOnClickListener(this)
        binding.likeWechatMiniPrograms.setOnClickListener(this)
        binding.btnBindService.setOnClickListener(this)
        binding.btnTimer.setOnClickListener(this)*/

        //省略写法
        setOnClickListener(
            binding.intentParcelable, binding.intentSerializable, binding.runtimePermission,
            binding.likeWechatMiniPrograms, binding.btnBindService, binding.btnTimer, binding.btnClock,
            binding.btnNewsScrolling,
            listener = this
        )
    }

    override fun onClick(v: View?) {
        //普通写法
        /*when (v?.id) {
            R.id.intent_parcelable -> startActivity(Intent(this,IntentParcelableActivity::class.java))
            R.id.intent_serializable -> startActivity(Intent(this,IntentSerializableActivity::class.java))
            R.id.runtime_permission -> startActivity(Intent(this,RuntimePermissionActivity::class.java))
            R.id.like_wechat_mini_programs -> startActivity(Intent(this,LikeWechatMiniProgramsActivity::class.java))
            R.id.btn_bind_service -> startActivity(Intent(this, BindServiceActivity::class.java))
        }*/
        //精简写法
        /*when (v?.id) {
            R.id.intent_parcelable -> toActivity(this,IntentParcelableActivity())
            R.id.intent_serializable -> toActivity(this,IntentSerializableActivity())
            R.id.runtime_permission -> toActivity(this,RuntimePermissionActivity())
            R.id.like_wechat_mini_programs -> toActivity(this,LikeWechatMiniProgramsActivity())
            R.id.btn_bind_service -> toActivity(this, BindServiceActivity())
        }*/
        //扩展函数（最优）
        when (v?.id) {
            R.id.intent_parcelable -> navigateTo(IntentParcelableActivity::class)
            R.id.intent_serializable -> navigateTo(IntentSerializableActivity::class)
            R.id.runtime_permission -> navigateTo(RuntimePermissionActivity::class)
            R.id.like_wechat_mini_programs -> navigateTo(LikeWechatMiniProgramsActivity::class)
            R.id.btn_bind_service -> navigateTo(BindServiceActivity::class)
            R.id.btn_timer -> navigateTo(TimerActivity::class)
            R.id.btn_clock -> navigateTo(ClockActivity::class)
            R.id.btn_news_scrolling -> navigateTo(NewsScrollingActivity::class)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}