package com.example.learnKotlin.chapter2

import kotlin.math.max

fun main(){
    val a = 37
    val b = 41
    val value = largerNumber(a,b)
    println("larger number is $value")

}
//函数的标准写法
fun largerNumber2(num1: Int,num2: Int) : Int{
    return max(num1,num2)
}
//kotlin语法糖
fun largerNumber(num1: Int,num2: Int) = max (num1 , num2)
fun largerNumber1(num1: Int,num2: Int) : Int = max (num1 , num2)