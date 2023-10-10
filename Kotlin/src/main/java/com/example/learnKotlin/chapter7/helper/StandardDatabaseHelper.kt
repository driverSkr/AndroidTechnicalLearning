package com.example.learnKotlin.chapter7.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class StandardDatabaseHelper private constructor(context: Context,version: Int = 1):
    SQLiteOpenHelper(context, DB_NAME,null, version)  {

    private var mDB: SQLiteDatabase? = null  // 数据库的实例

    companion object{
        private const val TAG = "StandardDatabaseHelper"
        private const val DB_NAME = "User.db" // 数据库的名称
        private var mHelper: StandardDatabaseHelper? = null // 数据库帮助器的实例
        const val TABLE_NAME = "user_info"  // 表的名称

        fun getInstance(context: Context,version: Int): StandardDatabaseHelper{
            if (version > 0 && mHelper == null){
                mHelper = StandardDatabaseHelper(context, version)
            } else if (mHelper == null){
                mHelper = StandardDatabaseHelper(context)
            }
            return mHelper!!
        }
    }

    // 打开数据库的读连接
    fun openReadLink(): SQLiteDatabase{
        if (mDB == null || !mDB!!.isOpen){
            mDB = readableDatabase
        }
        return mDB!!
    }

    // 打开数据库的写连接
    fun openWriteLink(): SQLiteDatabase {
        if (mDB == null || !mDB!!.isOpen) {
            mDB = writableDatabase
        }
        return mDB!!
    }

    // 关闭数据库连接
    fun closeLink(){
        if (mDB != null && mDB!!.isOpen) {
            mDB!!.close()
            mDB = null
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate")
        val dropSql = "DROP TABLE IF EXISTS $TABLE_NAME;"
        Log.d(TAG, "dropSql: $dropSql")
        db.execSQL(dropSql)
        val createSql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL," +
                "age INTEGER NOT NULL," +
                "height INTEGER NOT NULL," +
                "weight FLOAT NOT NULL," +
                "married INTEGER NOT NULL," +
                "update_time VARCHAR NOT NULL," +
                // 演示数据库升级时要先把下面这行注释
                "phone VARCHAR," +
                "password VARCHAR" +
                ");"
        Log.d(TAG, "createSql: $createSql")
        db.execSQL(createSql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade oldVersion=$oldVersion, newVersion=$newVersion")
        if (newVersion > 1){
            // Android的ALTER命令不支持一次添加多列，只能分多次添加
            val alterSql = "ALTER TABLE $TABLE_NAME ADD COLUMN phone VARCHAR;"
            Log.d(TAG, "alterSql: $alterSql")
            db.execSQL(alterSql)
            val alterSql2 = "ALTER TABLE $TABLE_NAME ADD COLUMN password VARCHAR;"
            Log.d(TAG, "alterSql2: $alterSql2")
            db.execSQL(alterSql2)
        }
    }

    //添加数据
    fun addData(){
        val value1 = ContentValues().apply {
            // 开始组装第一条数据
            put("name","邹聪波")
            put("age",23)
            put("height",180)
            put("weight",160.1)
            put("married",0)
            put("update_time","2023/10/10 11:22")
        }
        val result1 = mDB?.insert(TABLE_NAME,null,value1) // 插入第一条数据
        val values2 = ContentValues().apply {
            // 开始组装第二条数据
            put("name","肖玉龙")
            put("age",18)
            put("height",175)
            put("weight",140.5)
            put("married",0)
            put("update_time","2023/10/10 11:23")
        }
        val result2 = mDB?.insert(TABLE_NAME,null,values2) // 插入第二条数据

        Log.d(TAG,"result1 = $result1;result2 = $result2")
    }

    //更新数据
    fun updateData(){
        val values = ContentValues()
        values.put("weight",158.99)
        mDB?.update(TABLE_NAME,values,"name = ?", arrayOf("肖玉龙"))
    }

    //删除数据
    fun deleteData(){
        mDB?.delete(TABLE_NAME,"height > ?", arrayOf("178"))
    }

    //查询数据
    @SuppressLint("Range")
    fun queryData(): Cursor? {
        //查询Book表中所有的数据
        return mDB?.query(TABLE_NAME, null, null, null, null, null, null)
    }
}