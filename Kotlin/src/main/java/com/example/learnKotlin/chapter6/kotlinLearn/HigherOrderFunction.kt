package com.example.learnKotlin.chapter6.kotlinLearn

import java.lang.StringBuilder


fun main(){
    val num1 = 100
    val num2 = 80
    //普通写法
    val result1 = num1AndNum2(num1, num2,::plus)
    val result2 = num1AndNum2(num1,num2,::minus)
    //lambda表达式写法
    val result3 = num1AndNum2(num1,num2) { n1 , n2 ->
        n1 + n2 / 2
    }
    println("result1 is $result1")
    println("result2 is $result2")
    println("result3 is $result3")
}

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int,num2: Int): Int{
    return num1 + num2
}

fun minus(num1: Int,num2: Int): Int{
    return num1 - num2
}