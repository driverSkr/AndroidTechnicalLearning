package com.example.learnKotlin.learn

//集合函数式API
fun main(){
    //获取字母最长的单词
    getMaxLength()
}

fun getMaxLength(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit = list.maxBy { it.length }
    println("max length fruit is $maxLengthFruit")
}