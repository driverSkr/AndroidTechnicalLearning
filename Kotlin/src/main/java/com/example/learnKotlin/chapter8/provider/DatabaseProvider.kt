package com.example.learnKotlin.chapter8.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import com.example.learnKotlin.chapter7.helper.MyDatabaseHelper

//ContentProvider一定要在AndroidManifest.xml 文件中注册才可以使用
class DatabaseProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3
    private val authority = "com.example.learnKotlin.chapter8"
    private var dbHelper: MyDatabaseHelper? = null

    //by lazy代码块是Kotlin 提供的一种懒加载技术，代码块中的代码一开始并不会执行，只有当uriMatcher变量首次被调用的时候才会执行
    //并且会将代码块中最后一行代码的返回值赋给uriMatcher
    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority,"book",bookDir) //表示访问Book表中的所有数据
        matcher.addURI(authority, "book/#", bookItem) //访问Book表中的单条数据
        matcher.addURI(authority, "category", categoryDir) //访问Category表中的所有数据
        matcher.addURI(authority, "category/#", categoryItem) //访问Category表中的单条数据
        matcher
    }

    //使用的技术：Getter方法语法糖、?.操作符、let函数、?:操作符以及单行代码函数语法糖
    override fun onCreate() = context?.let {
        dbHelper = MyDatabaseHelper(it,"BookStore.db",2)
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper?.let {
        // 查询数据
        val db = it.readableDatabase
        val cursor = when(uriMatcher.match(uri)){
            bookDir -> db.query("Book",projection,selection,selectionArgs,null,null,sortOrder)
            bookItem -> {
                //它会将内容URI权限之后的部分以“/”符号进行分割，并把分割后的结果放入一个字符串列表中，
                // 那这个列表的第0个位置存放的就是路径，第1个位置存放的就是id了
                val bookId = uri.pathSegments[1]
                db.query("Book",projection,"id = ?", arrayOf(bookId),null,null,sortOrder)
            }
            categoryDir -> db.query("Category",projection,selection,selectionArgs,null,null,sortOrder)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("Category",projection,"id = ?", arrayOf(categoryId),null,null,sortOrder)
            }
            else -> null
        }
        cursor
    }

    //insert()方法要求返回一个能够表示这条新增数据的URI，所以我们还需要调用Uri.parse()方法，将一个内容URI解析成Uri对象
    //当然这个内容URI是以新增数据的id结尾的
    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
        // 添加数据
        val db = it.writableDatabase
        val uriReturn = when(uriMatcher.match(uri)){
            bookDir,bookItem -> {
                val newBookId = db.insert("Book",null,values)
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir,categoryItem -> {
                val newCategoryId = db.insert("Category",null,values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    //受影响的行数将作为返回值返回
    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {
        // 更新数据
        val db = it.writableDatabase
        val updatedRows = when(uriMatcher.match(uri)){
            bookDir -> db.update("Book",values,selection,selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("Book",values,"id = ?", arrayOf(bookId))
            }
            categoryDir -> db.update("Category",values,selection,selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category",values,"id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        updatedRows
    } ?: 0

    //被删除的行数将作为返回值返回
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = dbHelper?.let {
        // 删除数据
        val db = it.writableDatabase
        val deleteRows = when(uriMatcher.match(uri)){
            bookDir -> db.delete("Book",selection,selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.delete("Book","id = ?", arrayOf(bookId))
            }
            categoryDir -> db.delete("Category",selection,selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.delete("Category","id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        deleteRows
    } ?: 0

    override fun getType(uri: Uri) = when(uriMatcher.match(uri)){
        bookDir -> "vnd.android.cursor.dir/vnd.com.example.learnKotlin.chapter8.book"
        bookItem -> "vnd.android.cursor.item/vnd.com.example.learnKotlin.chapter8.book"
        categoryDir -> "vnd.android.cursor.dir/vnd.com.example.learnKotlin.chapter8.category"
        categoryItem -> "vnd.android.cursor.item/vnd.com.example.learnKotlin.chapter8.category"
        else -> null
    }
}