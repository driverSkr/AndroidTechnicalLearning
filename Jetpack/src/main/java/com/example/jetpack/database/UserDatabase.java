package com.example.jetpack.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.jetpack.dao.BookDao;
import com.example.jetpack.dao.UserDao;
import com.example.jetpack.entity.Book;
import com.example.jetpack.entity.User;

/*
 * Room框架
 * */
/*Database:用于定义数据库中的关键信息，包括数据库的版本号、包含哪些实体类以及提供Dao层的访问实例。*/
//entities表示该数据库有哪些表，version表示数据库的版本号
//exportSchema表示是否导出数据库信息的json串，建议设为false，
//              若设为true还需指定json文件的保存路径(写在了Module的build.gradle的defaultConfig{}里)
@Database(version = 1,entities = {User.class/*, Book.class*/},exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

   //只需要进行方法声明就可以了，具体的方法实现是由Room 在底层自动完成的
   public abstract UserDao userDao();
   //public abstract BookDao bookDao();

   private static UserDatabase instance;

   /*实现了一个Migration的匿名类，并传入了1和 2这两个参数，表示当数据库版本从1升级到2的时候就执行这个匿名类中的升级逻辑*/
   //匿名类实例的变量命名也比较有讲究，这里命名成MIGRATION_1_2，可读性更高

   /*public static final Migration MIGRATION_1_2 = new Migration(1,2) {
      @Override
      public void migrate(@NonNull SupportSQLiteDatabase database) {
         //由于我们要新增一张Book 表，所以需要在migrate()方法中编写相应的建表语句
         database.execSQL("create table Book (id integer primary key autoincrement not null, name text not null, pages integer not null)");
      }
   };*/

   public static synchronized UserDatabase getDatabase(Context context){
      if (instance != null){
         return instance;
      }
      //第一个参数一定要使用applicationContext，而不能使用普通的context，否则容易出现内存泄漏的情况
      //第二个参数是UserDatabase的Class类型
      //第三个参数是数据库名
      instance = Room.databaseBuilder(context.getApplicationContext(),
              UserDatabase.class,"user_database")
              //由于数据库操作属于耗时操作，Room 默认是不允许在主线程中进行数据库操作的
              //加入一个allowMainThreadQueries()方法，这样Room就允许在主线程中进行数据库操作了，这个方法建议只在测试环境下使用
              //.allowMainThreadQueries()
              //.addMigrations(MIGRATION_1_2)//升级数据库成版本2
              .build();
      return instance;
   }
}
