package com.example.learnKotlin.chapter2

//面向对象：创建一个类
/*一个类默认是不可以被继承的（类似java中的final），只有加了open关键字后才可以被继承*/
open class Person(val name: String,val age: Int) {
    /*var name = ""
    var age = 0*/

    fun eat(){
        println("$name is eating. He is $age years old.")
    }
}
fun main(){
    //Kotlin中实例化一个类的方式和Java是基本类似的，只是去掉了new关键字而已
    val p = Person("Jack",19)
    /*p.name = "Jack"
    p.age = 19*/
    p.eat()
}