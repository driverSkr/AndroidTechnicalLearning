package com.example.learnKotlin.chapter2

//Kotlin中的when语句有点类似于Java 中的switch语句，但它又远比switch语句强大得多
fun main(){
    val num = 10.5
    checkNumber(num)
}
//查询考试成绩:输入一个学生的姓名，返回该学生考试的分数
/*先用if语句来实现这个功能*/
fun getScore(name : String) = if (name == "Tom") {
    86
} else if (name == "Jim"){
    77
} else if (name == "Jack"){
    95
} else if (name == "Lily"){
    100
} else{
    0
}
/*使用when语句改写*/
//when语句和if语句一样，也是可以有返回值的
//匹配值 -> { 执行逻辑 }
fun getScore1(name: String) = when(name){
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}
/*when还可以不带参数的写法*/
//假设所有名字以Tom开头的人，他的分数都是86 分，这种场景如果用带参数的when语句来写就无法实现，而使用不带参数的when语句就可以
fun getScore2(name: String) = when{
    name.startsWith("Tom") -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}
//除了精确匹配之外，when语句还允许进行类型匹配
/*is关键字就是类型匹配的核心，它相当于Java 中的instanceof关键字*/
//Number类型的参数，这是Kotlin内置的一个抽象类，像Int、Long、Float、Double等与数字相关的类都是它的子类
fun checkNumber(num : Number){
    when(num){
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}
