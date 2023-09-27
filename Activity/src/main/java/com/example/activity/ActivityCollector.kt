package com.example.activity

import android.app.Activity

/*随时随地退出程序*/
//专门的集合对所有的Activity进行管理
//这里使用了单例类，是因为全局只需要一个Activity 集合。
object ActivityCollector {

    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    fun finishAll(){
        for (activity in activities){
            //判断Activity 是否正在销毁中
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }
}