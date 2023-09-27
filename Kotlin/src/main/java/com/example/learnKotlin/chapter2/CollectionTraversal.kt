package com.example.learnKotlin.learn

//集合遍历
fun main(){
    /*List集合*/
    //listOf是一个不可变的集合,只能用于读取， 无法对集合进行添加、修改或删除操作
    val list = listOf("Apple","Banana","Orange","Pear","Grape")
    //mutableListOf是一个可变的集合
    val mutableList = mutableListOf("Apple","Banana","Orange","Pear","Grape")
    mutableList.add("Watermelon")
    /*for (fruit in mutableList){
        println(fruit)
    }*/

    /*Set集合*/
    val set = setOf("Apple","Banana","Orange","Pear","Grape","Grape")
    /*for (fruit in set){
        println(fruit)
    }*/

    /*Map集合*/
    /*val map = HashMap<String,Int>()
    *map["Apple"] = 1
    map["Banana"] = 2
    map["Orange"] = 3
    map["Pear"] = 4
    map["Grape"] = 5*/
    val map = mapOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)
    for ((fruit,number) in map){
        println("fruit is $fruit, number is $number")
    }
}