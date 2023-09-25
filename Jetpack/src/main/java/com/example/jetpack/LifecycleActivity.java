package com.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jetpack.observer.MyObserver;

/*
 * Lifecycles:生命周期监听器
 * */
public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        /*LifecycleOwner.getLifecycle().addObserver(new Observer())*/
        /*如果Activity 是继承自AppCompatActivity的或者Fragment 是继承自androidx.fragment.app.Fragment的
        * 那么它们本身就是一个LifecycleOwner 的实例，这部分工作已经由AndroidX库自动帮我们完成了*/
        //添加生命周期监听，当遇到相应阶段时，会调用MyObserver对应时期的方法
        getLifecycle().addObserver(new MyObserver(getLifecycle()));
    }
}