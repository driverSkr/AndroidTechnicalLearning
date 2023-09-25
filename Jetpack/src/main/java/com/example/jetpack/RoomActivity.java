package com.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jetpack.dao.BookDao;
import com.example.jetpack.dao.UserDao;
import com.example.jetpack.database.AppDatabase;
import com.example.jetpack.database.UserDatabase;
import com.example.jetpack.entity.User;

/*Room数据库的简单实践*/
public class RoomActivity extends AppCompatActivity implements View.OnClickListener {

    private User user1;
    private User user2;
    private UserDao userDao;
    private UserDao appDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        findViewById(R.id.addDataBtn).setOnClickListener(this);
        findViewById(R.id.updateDataBtn).setOnClickListener(this);
        findViewById(R.id.deleteDataBtn).setOnClickListener(this);
        findViewById(R.id.queryDataBtn).setOnClickListener(this);

        userDao = UserDatabase.getDatabase(this).userDao();
        appDao = AppDatabase.getDatabase(this).userDao();
        user1 = new User("Tom", "Brady", 40);
        user2 = new User("Tom", "Hanks", 63);
    }

    /*由于数据库操作属于耗时操作，Room 默认是不允许在主线程中进行数据库操作的，因此上述代码中我们将增删改查的功能都放到了子线程中*/
    //在构建AppDatabase实例的时候，加入一个allowMainThreadQueries()方法，这样Room就允许在主线程中进行数据库操作了，这个方法建议只在测试环境下使用
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addDataBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user1.setId(appDao.insertUser(user1));
                       // user2.setId(userDao.insertUser(user2));
                    }
                }).start();
                break;
            case R.id.updateDataBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        user1.setAge(42);
                        userDao.updateUser(user1);
                    }
                }).start();
                break;
            case R.id.deleteDataBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDao.deleteUserByLastName("Hanks");
                    }
                }).start();
                break;
            case R.id.queryDataBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (User user : userDao.loadAllUsers()) {
                            Log.d("boge", user.toString());
                        }
                    }
                }).start();
                break;
        }
    }
}