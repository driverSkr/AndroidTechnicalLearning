package com.example.learnKotlin.chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.Kotlin.R
import com.example.learnKotlin.chapter7.database.AppDatabase
import com.example.learnKotlin.chapter7.entity.User
import kotlinx.android.synthetic.main.activity_room.*
import kotlin.concurrent.thread

class RoomActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "RoomActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom","Brady",40)
        val user2 = User("Tom","Hanks",63)

        //添加用户
        addDataBtn.setOnClickListener{
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        //更新用户数据
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        //删除用户数据
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        //查询用户数据
        queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()){
                    Log.d(TAG,user.toString())
                }
            }
        }
    }
}