package com.example.learnKotlin.chapter3

/*标准函数库：with、run*/
fun main(){
    //普通方法实现吃水果
    //eatFruitOrdinary()

    //使用with函数实现吃水果
    //eatFruitWith()

    //使用run函数实现吃水果
    //eatFruitRun()

    //使用apply函数来实现吃水果的代码
    eatFruitApply()
}

////使用with函数来实现吃水果的代码
fun eatFruitWith(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = with(StringBuilder()){
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        //最后一行即为函数的返回值
        toString()
    }
    println(result)
}

//使用run函数来实现吃水果的代码
fun eatFruitRun(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().run {
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        //最后一行即为函数的返回值
        toString()
    }
    println(result)
}

//使用apply函数来实现吃水果的代码
fun eatFruitApply(){
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().apply {
        append("Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
        //apply函数无法指定返回值，只能返回调用对象本身
    }
    println(result.toString())
}

//普通方法实现吃水果
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