package com.example.learnKotlin.chapter11.dao

import com.example.learnKotlin.chapter11.entity.PushMessage
import okhttp3.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PushService {

    @GET
    fun getPushMessage(@Url url: String): PushMessage
}