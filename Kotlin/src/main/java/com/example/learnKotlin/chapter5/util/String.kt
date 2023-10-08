package com.example.learnKotlin.chapter5.util

/*给String类实现扩展函数*/

//测试
fun main(){
    val count = "ABC123xyz!@#".lettersCount()
    println(count)
}

//统计字符串中英文字母的数量
fun String.lettersCount(): Int{
    var count = 0
    for (char in this){
        if (char.isLetter()){
            count ++
        }
    }
    return count
}

//重载乘号运算符
operator fun String.times(n: Int): String{
    val builder = StringBuilder()
    repeat(n){
        builder.append(this)
    }
    return builder.toString()
}