package com.example.jetpack.ViewModel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.jetpack.entity.Repository;
import com.example.jetpack.entity.User;

/*
* switchMap():LiveData对象的实例都是在ViewModel中创建的。然而在实际的项目中，不可能一直是这种理想情况，很有可能ViewModel中的某个LiveData对象是调用另外的方法获取的。
* */
public class LiveDataSwitchMapViewModel extends ViewModel {

    private MutableLiveData<String> userIdLiveData = new MutableLiveData<>();
    //当调用过getUser()方法后，switchMap函数观察到userIdLiveData有变化，执行apply()方法返回一个可观察的LiveData变量
    //外部调用时，只需观察user值即可
    public LiveData<User> user = Transformations.switchMap(userIdLiveData, new Function<String, LiveData<User>>() {
        @Override
        public LiveData<User> apply(String userId) {
            return Repository.getInstance().getUser(userId);
        }
    });

    //当调用这个方法时，userIdLiveData会传入一个userId值
    public void getUser(String userId) {
        userIdLiveData.setValue(userId);
    }

}

