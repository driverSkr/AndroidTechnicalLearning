package com.example.learnKotlin.chapter5.util

/*运算符重载*/

fun main(){
    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    val money4 = money3 + 20
    println(money3.value)
    println(money4.value)
}

class Money(val value: Int){
    operator fun plus(money: Money): Money{
        val sum = value + money.value
        return Money(sum)
    }
    operator fun plus(newValue: Int): Money{
        val sum = value + newValue
        return Money(sum)
    }
}