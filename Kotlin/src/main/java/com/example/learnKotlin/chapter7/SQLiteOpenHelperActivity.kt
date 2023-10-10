package com.example.learnKotlin.chapter7

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.Kotlin.R
import com.example.learnKotlin.chapter7.helper.MyDatabaseHelper
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.addData
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.createDatabase
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.deleteData
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.queryData
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.replaceData
import kotlinx.android.synthetic.main.activity_sqlite_open_helper.updateData
import java.lang.Exception

/*创建数据库、添加数据、更新数据、删除数据、查询数据*/
class SQLiteOpenHelperActivity : AppCompatActivity() {

    private val TAG = "SQLiteOpenHelperActivity"

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_open_helper)

        //将数据库名指定为BookStore.db ，版本号指定为1（想执行onUpgrade方法，需将版本号设为比之前的大）
        val dbHelper = MyDatabaseHelper(this,"BookStore.db",2)
        //创建数据库
        createDatabase.setOnClickListener{
            dbHelper.writableDatabase
        }
        //添加数据
        addData.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name","The Da Vinci Code")
                put("author","Dan Brown")
                put("pages",454)
                put("price",16.96)
            }
            db.insert("Book",null,values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据
        }

        //更新数据
        updateData.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price",10.99)
            //第三个参数对应的是SQL语句的where部分，表示更新所有name等于?的行
            //而?是一个占位符，可以通过第四个参数提供的一个字符串数组为第三个参数中的每个占位符指定相应的内容
            //arrayOf()方法是Kotlin提供的一种用于便捷创建数组的内置方法
            db.update("Book",values,"name = ?", arrayOf("The Da Vinci Code"))
        }

        //删除数据
        deleteData.setOnClickListener{
            val db = dbHelper.writableDatabase
            //通过第二、第三个参数来指定仅删除那些页数超过500 页的书
            db.delete("Book","pages > ?", arrayOf("500"))
        }

        //查询数据
        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            //查询Book表中所有的数据
            val cursor = db.query("Book",null,null,null,null,null,null)
            if (cursor.moveToFirst()){
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d(TAG, "book name is $name")
                    Log.d(TAG, "book author is $author")
                    Log.d(TAG, "book pages is $pages")
                    Log.d(TAG, "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        /*使用事务*/
        //替换Book表的数据（删除旧数据和添加新数据的操作必须一起完成，否则就要继续保留原来的旧数据）
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() // 开启事务
            try {
                db.delete("Book",null,null)
                /*if (true){
                    // 手动抛出一个异常，让事务失败
                    throw NullPointerException()
                }*/
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book",null,values)
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }
        }
    }
}