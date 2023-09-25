package com.example.jetpack.entity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/*
* LiveData的switchMap()方法
* */
//新建Repository单例类，在ViewModel外部生成User的LiveData对象
public class Repository {

    private static Repository instance;

    private Repository() {
        // 私有构造函数，防止外部实例化
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    //返回的是一个包含User数据的LiveData对象，
    // 而且每次调用getUser()方法都会返回一个新的LiveData 实例
    public LiveData<User> getUser(String userId) {
        MutableLiveData<User> liveData = new MutableLiveData<>();
        liveData.setValue(new User(userId, userId, 0));
        return liveData;
    }
}

