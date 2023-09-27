package com.example.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    //所有定义在companion object中的方法都可以使用类似于Java 静态方法的形式调用
    companion object {
        fun actionStart(context: Context,data1: String,data2: String){
            val intent = Intent(context,SecondActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }
}