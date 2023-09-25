package com.example.jetpack.ViewModel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.jetpack.entity.User;

/*
* map():LiveData 为了能够应对各种不同的需求场景，提供了两种转换方法map()和switchMap()方法
* */
//作用是将实际包含数据的LiveData 和仅用于观察数据的LiveData 进行转换(只会显示用户的姓名，而完全不关心用户的年龄，那么这个时候还将整个User类型的LiveData 暴露给外部，就显得不那么合适了)
public class LiveDataMapViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    //map()方法的用法和使用场景
    //当userLiveData的数据发生变化时，map()方法会监听到userLiveData的变化并执行转换函数中的逻辑，
    // 然后再将转换之后的数据通知给userName的观察者，外部使用只需观察userName即可
    public LiveData<String> userName = Transformations.map(userLiveData, new Function<User, String>() {
        @Override
        public String apply(User user) {
            return user.getFirstName() + " " + user.getLastName();
        }
    });

    public LiveDataMapViewModel(User user){
        userLiveData.setValue(user);
    }

    public MutableLiveData<User> getUserLiveData(){
        return userLiveData;
    }
}
