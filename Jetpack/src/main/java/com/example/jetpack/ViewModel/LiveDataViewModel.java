package com.example.jetpack.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/*
* LiveData 是Jetpack 提供的一种响应式编程组件，它可以包含任何类型的数据，并在数据发生变化的时候通知给观察者
* MutableLiveData是一种可变的LiveData:主要有3种读写数据的方法，分别是getValue()、setValue()和postValue()方法
* */
//getValue()方法用于获取LiveData 中包含的数据
//setValue()方法用于给LiveData 设置数据，但是只能在主线程中调用
//postValue()方法用于在非主线程中给LiveData 设置数据
public class LiveDataViewModel extends ViewModel {

    //将counter变量修改成了一个MutableLiveData对象，并指定它的泛型为Int，表示它包含的是整型数据
    private final MutableLiveData<Integer> counter = new MutableLiveData<>();

    //构造函数
    public LiveDataViewModel(int countReserved) {
        counter.setValue(countReserved);
    }

    public MutableLiveData<Integer> getCounter() {
        return counter;
    }

    public void plusOne() {
        Integer count = counter.getValue();
        counter.setValue(count != null ? count + 1 : 0);
    }

    public void clear() {
        counter.setValue(0);
    }
}

