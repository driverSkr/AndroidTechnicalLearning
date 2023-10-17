package com.example.learnKotlin.chapter11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){
    runBlocking {
        val result = withContext(Dispatchers.Default){
            5 + 5
        }
        println(result)
    }
}