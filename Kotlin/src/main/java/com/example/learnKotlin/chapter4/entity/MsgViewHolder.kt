package com.example.learnKotlin.chapter4.entity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Kotlin.R

//密封类
/*密封类及其所有子类只能定义在同一个文件的顶层位置，不能嵌套在其他类中，这是被密封类底层的实现机制所限制的。*/
/*当在when语句中传入一个密封类变量作为条件时，Kotlin 编译器会自动检查该密封类有哪些子类，并强制要求你将每一个子类所对应的条件全部处理*/
sealed class MsgViewHolder(view: View): RecyclerView.ViewHolder(view)

class LeftViewHolder(view: View): MsgViewHolder(view){
    val leftMsg: TextView = view.findViewById(R.id.leftMsg)
}

class RightViewHolder(view: View) : MsgViewHolder(view) {
    val rightMsg: TextView = view.findViewById(R.id.rightMsg)
}
