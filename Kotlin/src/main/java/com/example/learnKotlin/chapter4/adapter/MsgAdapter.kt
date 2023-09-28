package com.example.learnKotlin.chapter4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Kotlin.R
import com.example.learnKotlin.chapter4.entity.LeftViewHolder
import com.example.learnKotlin.chapter4.entity.Msg
import com.example.learnKotlin.chapter4.entity.MsgViewHolder
import com.example.learnKotlin.chapter4.entity.RightViewHolder


class MsgAdapter(private val msgList: List<Msg>): RecyclerView.Adapter<MsgViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == Msg.TYPE_RECEIVED){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
        LeftViewHolder(view)
    } else {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
        }
    }

    override fun getItemCount() = msgList.size

}