package com.example.componentstest

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_provider_test.*

class ProviderTestActivity : AppCompatActivity() {

    private var bookId: String? = null

    companion object{
        private const val TAG = "ProviderTestActivity"
    }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_test)

        addData.setOnClickListener{
            //添加数据
            val uri = Uri.parse("content://com.example.learnKotlin.chapter8/book")
            val values = contentValuesOf("name" to "A Clash of Kings",
                "author" to "George Martin", "pages" to 1040, "price" to 22.85)
            val newUri = contentResolver.insert(uri,values)
            bookId = newUri?.pathSegments?.get(1)
        }

        queryData.setOnClickListener{
            // 查询数据
            val uri = Uri.parse("content://com.example.learnKotlin.chapter8/book")
            contentResolver.query(uri,null,null,null,null)?.apply {
                while (moveToNext()){
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getDouble(getColumnIndex("price"))
                    Log.d(TAG, "book name is $name")
                    Log.d(TAG, "book author is $author")
                    Log.d(TAG, "book pages is $pages")
                    Log.d(TAG, "book price is $price")
                }
                close()
            }
        }

        updateData.setOnClickListener{
            // 更新数据
            bookId?.let {
                val uri = Uri.parse("content://com.example.learnKotlin.chapter8/book/$it")
                val values = contentValuesOf("name" to "A Storm of Swords",
                    "pages" to 1216, "price" to 24.05)
                contentResolver.update(uri,values,null,null)
            }
        }

        deleteData.setOnClickListener{
            // 删除数据
            bookId?.let {
                val uri = Uri.parse("content://com.example.learnKotlin.chapter8/book/$it")
                contentResolver.delete(uri,null,null)
            }
        }
    }
}