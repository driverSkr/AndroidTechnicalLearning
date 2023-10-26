package com.example.internet.utils

import com.example.internet.interfaces.EyeOpeningService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 开眼-网络基础服务配置。
 */
object EyeOpeningServiceCreator {

    const val BASE_URL = "http://baobab.kaiyanapp.com/"

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    fun getDefaultService() = create(EyeOpeningService::class.java)
}