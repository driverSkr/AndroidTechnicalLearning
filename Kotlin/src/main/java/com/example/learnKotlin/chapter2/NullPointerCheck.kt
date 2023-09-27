package com.example.learnKotlin.learn

//空指针检查

var content: String? = "hello"

fun main() {
    doStudy1(null)

    if (content != null){
        printUpperCase()
    }
}

//设置可为空参数
fun doStudy1(study: Study?){
    //基础写法
    if (study != null){
        study.readBooks()
        study.doHomework()
    }
    //?.操作符改写
    study?.readBooks()
    study?.doHomework()

    //?.和let函数一起使用
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}

//结合使用?.和?:
/*首先由于text是可能为空的，因此我们在调用它的length字段时需要使用?.操作符，而当text为空时，text?.length会返回一个null值，这个时候我们再借助?:操作符让它返回0*/
fun getTextLength(text: String?) = text?.length ?: 0

/*非空断言工具：!!*/
fun printUpperCase(){
    //因为printUpperCase()函数并不知道外部已经对content变量进行了非空检查，在调用
    //toUpperCase()方法时，还认为这里存在空指针风险，从而无法编译通过。
    /*在这种情况下，如果我们想要强行通过编译，可以使用非空断言工具*/
    val upperCase = content!!.uppercase()
    println(upperCase)
}