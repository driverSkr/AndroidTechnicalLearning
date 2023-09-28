package com.example.learnKotlin.chapter2

//while循环不管是在语法还是使用技巧上都和Java 中的while循环没有任何区别
fun main(){
    for (i in 0 until 10){
        println(i)
    }
//-------------使用step跳过区间内的元素-------------------
    //step 2 ==>i = i + 2的效果
    for(i in 0 until 10 step 2){
        println(i)
    }
//--------------使用downTo创建一个降序的区间[10, 1]--------------------
    for (i in 10 downTo 1){
        println(i)
    }

}
/*Java中最常用的for-i循环在Kotlin中直接被舍弃了，
而Java 中另一种for-each循环则被Kotlin进行了大幅度的加强，变成了for-in循环，
所以我们只需要学习for-in循环的用法就可以了*/
//我们可以使用如下Kotlin代码来表示一个区间：
val range = 0..10 // =>[0,10]
//Kotlin中可以使用until关键字来创建一个左闭右开的区间
val range2 = 0 until 10 // =>[0,10)