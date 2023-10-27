package com.example.jetpack.utils

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * Flow的操作符函数
 */

val flow = flowOf(1, 2, 3, 4, 5)

fun main(){
    runBlocking {
        //mapFun()

        filterFun()
    }
}

/** 1.map 将一个值映射成另一个值，具体映射的规则在map函数中自行定义**/
suspend fun mapFun(){
    flow.map {
        it * it
    }.collect {
        println(it)
    }
}

/** 2.filter 过滤掉一些数据 **/
suspend fun filterFun(){
    flow.filter {
        it % 2 == 0
    }.map {
        it * it
    }.collect {
        println(it)
    }
}