package com.example.learnKotlin.learn

//要让Student类继承Person类。在Java中继承的关键字是extends，而在Kotlin中变成了一个冒号
class Student(name: String,age: Int) : Person(name, age), Study {
    override fun readBooks() {
        println("$name is reading.")
    }

    //对于接口的doHomework进行了默认实现，不是必须要重写了
    /*override fun doHomework() {
        println("$name is doing homework.")
    }*/

}

fun main() {
    val student = Student("Java",19)
    val phone = CellphoneJava("华为",5999.0);
    println("${phone.brand} 现在起售价为 ${phone.price}")
    //doStudy(student)
}

fun doStudy(study: Study){
    study.readBooks()
    study.doHomework()
}