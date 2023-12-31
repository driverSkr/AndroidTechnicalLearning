package com.example.internet.entity

import com.google.gson.annotations.SerializedName

/**
 * 通知-推送消息列表，响应实体类。
 *
 * @author driverSkr
 * @since  2023/10/18
 */
data class PushMessage(@SerializedName("messageList") val itemList: List<Message>, val nextPageUrl: String?, val updateTime: Long) {

    data class Message(
        val actionUrl: String,
        val content: String,
        val date: Long,
        val icon: String?,
        val id: Int,
        val ifPush: Boolean,
        val pushStatus: Int,
        val title: String?,
        val uid: Any,
        val viewed: Boolean
    )
}

