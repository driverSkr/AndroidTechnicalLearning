package com.example.learnKotlin.chapter7.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.learnKotlin.chapter7.dao.BookDao
import com.example.learnKotlin.chapter7.dao.UserDao
import com.example.learnKotlin.chapter7.entity.Book
import com.example.learnKotlin.chapter7.entity.User

@Database(version = 3, entities = [User::class, Book::class],exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao

    //在companion object结构体中编写了一个单例模式，因为原则上全局应该只存在一份AppDatabase的实例
    companion object{

        private val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key autoincrement not null, name text not null, pages integer not null)")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null\n" +
                        " default 'unknown'")
            }
        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase{
            instance?.let { return it }
            //第一个参数一定要使用applicationContext，而不能使用普通的context，否则容易出现内存泄漏的情况
            //第二个参数是AppDatabase的Class类型
            //第三个参数是数据库名
            return Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,"app_database")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build().apply {
                    instance = this
                }
        }
    }
}