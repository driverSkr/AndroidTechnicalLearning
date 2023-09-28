package com.example.learnKotlin.chapter2

//集合函数式API
fun main(){
    //获取字母最长的单词
    //getMaxLength()

    //让集合变大写模式
    //mToUpperCase()

    //保留5个字母以内的水果,同时大写
    //filterMap()

    //any函数用于判断集合中是否至少存在一个元素满足指定条件
    //all函数用于判断集合中是否所有元素都满足指定条件
    any1all()
}

fun getMaxLength(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit = list.maxBy { it.length }
    println("max length fruit is $maxLengthFruit")
}

fun mToUpperCase(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val newList = list.map { it.uppercase() }
    for (fruit in newList){
        println(fruit)
    }
}

fun filterMap(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val newList = list.filter { it.length <= 5 }
                      .map { it.uppercase() }
    for (fruit in newList){
        println(fruit)
    }
}

fun any1all(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is $anyResult, allResult is $allResult")
}