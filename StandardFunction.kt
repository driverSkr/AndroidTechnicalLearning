package com.example.learnKotlin.chapter3

fun main(){
    //普通方法实现吃水果
    //eatFruitOrdinary()

    //使用with函数实现吃水果
    eatFruitWith()
}

fun eatFruitWith(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = with(StringBuilder()){
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        toString()
    }
    println(result)
}

fun eatFruitOrdinary(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val builder = StringBuilder()
    builder.append("Start eating fruits.\n")
    for (fruit in list) {
        builder.append(fruit).append("\n")
    }
    builder.append("Ate all fruits.")
    val result = builder.toString()
    println(result)
}