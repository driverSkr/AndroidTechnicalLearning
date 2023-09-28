package com.example.learnKotlin.chapter2

fun main(){
    runnableSimplify()
}

//对于Runnable的简写过程（lambda)
fun runnableSimplify(){
    //完整写法
    /*Thread(object : Runnable {
        override fun run() {
            println("Thread is running")
        }
    }).start()*/

    //因为Runnable类中只有一个待实现方法，即使这里没有显式地重写run()方法，Kotlin也能自动明白Runnable后面的Lambda表达式就是要在run()方法中实现的内容
    /*Thread(Runnable {
        println("Thread is running")
    }).start()*/

    //如果一个Java 方法的参数列表中有且仅有一个Java单抽象方法接口参数，我们还可以将接口名进行省略
    /*Thread({
        println("Thread is running")
    }).start()*/

    //当Lambda 表达式是方法的最后一个参数时，可以将Lambda 表达式移到方法括号的外面。同时，如果Lambda 表达式还是 方法的唯一一个参数，还可以将方法的括号省略
    Thread {
        println("Thread is running")
    }.start()
}