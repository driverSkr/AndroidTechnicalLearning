package com.example.internet.interfaces

import com.example.internet.entity.Discovery
import com.example.internet.entity.PushMessage
import com.example.internet.utils.EyeOpeningServiceCreator
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * 获取开眼提供的数据
 */
interface EyeOpeningService {

    /**
     * 首页-发现列表
     */
    @GET
    suspend fun getDiscovery(@Url url: String): Discovery

    /**
     * 通知-推送列表
     */
    @GET
    suspend fun getPushMessage(@Url url: String): PushMessage


    companion object {
        /**
         * 首页-发现列表
         */
        const val DISCOVERY_URL = "${EyeOpeningServiceCreator.BASE_URL}api/v7/index/tab/discovery"

        /**
         * 通知-推送列表
         */
        const val PUSH_MESSAGE_URL = "${EyeOpeningServiceCreator.BASE_URL}api/v3/messages"
    }
}