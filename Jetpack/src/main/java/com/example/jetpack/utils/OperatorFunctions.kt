package com.example.jetpack.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Flow的操作符函数
 */

val flow = flowOf(1, 2, 3, 4, 5)

fun main(){
    runBlocking {
        //mapFun()
        //filterFun()
        //onEachFun()
        //debounceFun()
        //sampleFun()
        reduceFun()
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

/** 3.onEach 遍历每一条数据的**/
//collect函数中打印出的是最终的结果。如果你想要查看某个中间状态时flow的数据状态，借助onEach就非常有用了
suspend fun onEachFun(){
    runBlocking {
        flow.onEach {
            println(it)
        }.collect {
        }
    }
}

/** 4.debounce 确保flow的各项数据之间存在一定的时间间隔，如果是时间点过于临近的数据只会保留最后一条**/
@OptIn(FlowPreview::class)
suspend fun debounceFun(){
    runBlocking {
        flow {
            emit(1)
            emit(2)
            delay(600)
            emit(3)
            delay(100)
            emit(4)
            delay(100)
            emit(5)
        }.debounce(500).collect{
                println(it)
            }
    }
}

/** 5.sample 采样的意思，也就是说，它可以从flow的数据流当中按照一定的时间间隔来采样某一条数据**/
suspend fun sampleFun(){
    runBlocking {
        flow {
            while (true) {
                emit("发送一条弹幕")
            }
        }
        .sample(1000)
        //由于flow是通过死循环不断发送的，我们必须调用flowOn函数，让它在IO线程里去执行，否则我们的主线程就一直被卡死了
        .flowOn(Dispatchers.IO)
        .collect {
            println(it)
        }
    }
}
/** 6.reduce **/
suspend fun reduceFun(){
    runBlocking {
        val result = flow {
            for (i in (1..100)) {
                emit(i)
            }
        }.reduce { accumulator, value -> accumulator + value }
        println(result)
    }
}

