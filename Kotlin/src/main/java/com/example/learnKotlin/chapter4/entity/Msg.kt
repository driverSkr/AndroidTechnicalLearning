package com.example.learnKotlin.chapter4.entity

/*消息实体，用于发送的消息*/
//content表示消息的内容，type表示消息的类型
class Msg(val content:String,val type: Int) {
    companion object{
    //定义常量的关键字是const;注意只有在单例类、companion object 或顶层方法中才可以使用const关键字
        //TYPE_RECEIVED表示这是一条收到的消息
        const val TYPE_RECEIVED = 0
        //TYPE_SENT表示这是一条发出的消息
        const val TYPE_SENT = 1
    }
}