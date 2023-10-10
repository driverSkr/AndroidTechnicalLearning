package com.example.learnKotlin.chapter7

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.Kotlin.R
import com.example.learnKotlin.chapter7.helper.StandardDatabaseHelper
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.*
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.addData
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.deleteData
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.queryData
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.updateData
import kotlinx.android.synthetic.main.activity_standard_database_helper.*

class StandardDatabaseHelperActivity : AppCompatActivity(), View.OnClickListener {

    private var mHelper:StandardDatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard_database_helper)

        mHelper = StandardDatabaseHelper.getInstance(this,2)
        mHelper?.openWriteLink()

        addData.setOnClickListener(this)
        deleteData.setOnClickListener(this)
        updateData.setOnClickListener(this)
        queryData.setOnClickListener(this)
    }

    @SuppressLint("Range")
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.addData -> mHelper?.addData()
            R.id.deleteData -> mHelper?.deleteData()
            R.id.updateData -> mHelper?.updateData()
            R.id.queryData -> {
                val queryData = mHelper?.queryData()
                val showData = StringBuffer()
                if (queryData?.moveToFirst() == true){
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        val name = queryData.getString(queryData.getColumnIndex("name"))
                        val age = queryData.getInt(queryData.getColumnIndex("age"))
                        val height = queryData.getInt(queryData.getColumnIndex("height"))
                        val weight = queryData.getFloat(queryData.getColumnIndex("weight"))
                        val married = queryData.getInt(queryData.getColumnIndex("married"))
                        val updateTime = queryData.getString(queryData.getColumnIndex("update_time"))
                        showData.apply {
                            append("$name ")
                            append("$age ")
                            append("$height ")
                            append("$weight ")
                            append("$married ")
                            append("$updateTime \n")
                        }
                    } while (queryData.moveToNext())
                }
                dataShow.text = showData
                queryData?.close()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHelper?.closeLink()
    }
}