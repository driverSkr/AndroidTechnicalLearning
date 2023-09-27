package com.example.learnKotlin.learn

//kotlin的一些使用技巧

fun main(){
    printParams(123)

    //可以以键值对的形式传参，防止歧义
    printParams1(str = "world")
}

fun printParams(num: Int,str: String = "hello"){
    println("num is $num, str is $str")
}

//给第一个参数设置默认值
fun printParams1(num: Int = 100,str: String){
    println("num is $num, str is $str")
}