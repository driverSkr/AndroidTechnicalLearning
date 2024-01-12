package com.example.advancedskill.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import kotlin.reflect.KClass

/**
 * @Author: driverSkr
 * @Time: 2024/1/10 10:36
 * @Description: 全局事件$
 */

/**
 * 批量设置控件点击事件。
 *
 * @param v 点击的控件
 * @param block 处理点击事件回调代码块
 */
fun setOnClickListener(vararg v: View?, block: View.() -> Unit) {
    val listener = View.OnClickListener { it.block() }
    v.forEach { it?.setOnClickListener(listener) }
}

/**
 * 批量设置控件点击事件。
 *
 * @param v 点击的控件
 * @param listener 处理点击事件监听器
 */
fun setOnClickListener(vararg v: View?, listener: View.OnClickListener) {
    v.forEach { it?.setOnClickListener(listener) }
}

/**
 * Intent跳转
 */
fun toActivity(activity: Activity, toActivity: Activity) {
    activity.startActivity(Intent(activity, toActivity::class.java))
}

/**
 * Intent跳转（优化）
 * out 关键字是协变（Covariance）的指示符,当方法需要一个 KClass<Activity> 参数时，你可以传递任何 Activity 的子类的 KClass 实例
 */
fun Context.navigateTo(activityClass: KClass<out Activity>) {
    startActivity(Intent(this, activityClass.java))
}