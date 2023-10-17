package com.example.learnKotlin.chapter11

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//使用Global.launch函数创建协程
fun main(){
    GlobalScope.launch{
        println("codes run in coroutine scope")
    }
    Thread.sleep(1000)
}