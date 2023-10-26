package com.example.internet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.internet.databinding.ActivityEyeOpeningBinding
import com.example.internet.interfaces.EyeOpeningService
import com.example.internet.utils.EyeOpeningServiceCreator
import com.example.internet.utils.logD
import kotlinx.coroutines.runBlocking

class EyeOpeningActivity : AppCompatActivity() {

    private var _binding: ActivityEyeOpeningBinding? = null

    private val binding: ActivityEyeOpeningBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityEyeOpeningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickDiscovery.setOnClickListener {
            runBlocking {
                getDiscoveryData()
            }
        }
        binding.clickNotificationPush.setOnClickListener {
            runBlocking {
                getPushData()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * 获取首页-发现列表
     */
    private suspend fun getDiscoveryData(){
        try {
            val eyeOpeningService = EyeOpeningServiceCreator.getDefaultService()
            val repoResponse = eyeOpeningService.getDiscovery(EyeOpeningService.DISCOVERY_URL)
            binding.showEyeOpenData.text = repoResponse.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 获取通知-推送列表
     */
    private suspend fun getPushData(){
        try {
            val repoResponse = EyeOpeningServiceCreator.getDefaultService().getPushMessage(EyeOpeningService.PUSH_MESSAGE_URL)
            binding.showEyeOpenData.text = repoResponse.toString()
            logD(msg = repoResponse.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}