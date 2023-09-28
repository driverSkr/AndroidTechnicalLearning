package com.example.learnKotlin.chapter3

/*使用上@JvmStatic注解创建静态方法*/
class AnnotationsCreateStaticFunction {

    fun doAction1(){
        println("do action1")
    }

    companion object{
        //@JvmStatic注解只能加在单例类或companion object中的方法上，如果你尝试加在 一个普通方法上，会直接提示语法错误
        @JvmStatic
        fun doAction2(){
            println("do action2")
        }
    }
}