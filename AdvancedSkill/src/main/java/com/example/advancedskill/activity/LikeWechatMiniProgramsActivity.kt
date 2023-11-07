package com.example.advancedskill.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.advancedskill.R
import com.example.advancedskill.databinding.ActivityLikeWechatMiniProgramsBinding
import com.example.advancedskill.test.FirstActivity
import com.example.advancedskill.test.SecondActivity
import com.example.advancedskill.test.ThirdActivity

/**
 * 仿微信小程序样式
 */
class LikeWechatMiniProgramsActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding:ActivityLikeWechatMiniProgramsBinding? = null

    private val binding:ActivityLikeWechatMiniProgramsBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLikeWechatMiniProgramsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.firstBtn.setOnClickListener(this)
        binding.secondBtn.setOnClickListener(this)
        binding.thirdBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.first_btn -> startActivity(Intent(this,FirstActivity::class.java))
            R.id.second_btn -> startActivity(Intent(this,SecondActivity::class.java))
            R.id.third_btn -> startActivity(Intent(this,ThirdActivity::class.java))
        }
    }
}