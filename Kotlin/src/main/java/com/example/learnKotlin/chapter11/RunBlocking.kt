package com.example.learnKotlin.chapter11

import kotlinx.coroutines.*

//使用runBlocking开启协程
fun main(){
    runBlocking {
        val result = async {
            5 + 5
        }.await()
        println(result)
    }
}