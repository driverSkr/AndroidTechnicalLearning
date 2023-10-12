package com.example.learnKotlin.chapter7.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/*第一个参数是Context，这个没什么好说的，必须有它才能对数据库进行操作；
第二个参数是数据库名，创建数据库时使用的就是这里指定的名称；
第三个参数允许我们在查询数据的时候返回一个自定义的Cursor ，一般传入null即可；
第四个参数表示当前数据库的版本号，可用于对数据库进行升级操作*/
class MyDatabaseHelper(val context: Context,name: String,version: Int):
        SQLiteOpenHelper(context, name,null, version) {

    //第一次创建的表
    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"
    //第二次创建的表（需在onUpgrade中删除原存在的表)
    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
        //跨程序访问时不能直接使用Toast
        //Toast.makeText(context,"Create succeeded",Toast.LENGTH_SHORT).show()
    }

    /*想新增一张表，需要在onUpgrade中删除原存在的表，并重新执行onCreate*/
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //如果发现数据库中已经存在Book表或Category表，就将这两张表删除，然后调用onCreate()方法重新创建
        db?.execSQL("drop table if exists Book")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }

}