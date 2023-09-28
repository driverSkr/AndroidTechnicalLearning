package com.example.learnKotlin.chapter2

fun main(){

}
//1.初始if写法
fun largerNumber4(num1 : Int,num2 : Int): Int{
    var value = 0
    if (num1 > num2){
        value = num1
    } else{
        value = num2
    }
    return value
}
//2.if语句可以有返回值:返回值就是if语句每一个条件中最后一行代码的返回值
fun largerNumber5(num1 : Int,num2 : Int): Int{
    val value = if (num1 > num2){
        num1
    } else{
        num2
    }
    return value
}
//3.if更精简写法
fun largerNumber6(num1 : Int,num2 : Int): Int{
    return if (num1 > num2){
        num1
    } else{
        num2
    }
}
//4.使用kotlin语法糖，再次精简
fun largerNumber7(num1: Int,num2: Int) = if (num1 > num2){
    num1
} else {
    num2
}
//5.结合if特性，再再精简
fun largerNumber8 (num1: Int,num2: Int) = if (num1 > num2) num1 else num2