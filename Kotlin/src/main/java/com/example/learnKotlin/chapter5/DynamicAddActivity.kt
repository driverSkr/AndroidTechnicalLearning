package com.example.learnKotlin.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.Kotlin.R
import com.example.learnKotlin.chapter5.fragment.AnotherRightFragment
import com.example.learnKotlin.chapter5.fragment.RightFragment
import kotlinx.android.synthetic.main.activity_alert_dialog.*

//代码中动态引入Fragment
class DynamicAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_add)

        //左侧Fragment 中的按钮注册了一个点击事件
        button.setOnClickListener{
            //当点击左侧Fragment 中的按钮时，又会调用replaceFragment()方法，将右侧Fragment 替换成AnotherRightF ragment
            replaceFragment(AnotherRightFragment())
        }
        //动态添加了RightFragment
        replaceFragment(RightFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout,fragment)
        //将一个事务添加到返回栈中
        transaction.addToBackStack(null)
        transaction.commit()
    }
}