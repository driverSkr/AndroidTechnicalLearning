package com.example.learnKotlin.chapter6.kotlinLearn

import java.lang.StringBuilder

fun main(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())
}

//使用高阶函数模仿实现apply函数类似的功能
//在函数类型的前面加上ClassName. 就表示这个函数类型是定义在哪个类当中的
fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}