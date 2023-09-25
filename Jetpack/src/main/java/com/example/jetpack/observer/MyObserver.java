package com.example.jetpack.observer;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/*
* Lifecycles:生命周期监听器
* */
//生命周期事件的类型一共有7种：ON_CREATE、ON_START、ON_RESUME、ON_PAUSE、ON_STOP和ON_DESTROY分别匹配Activity 中相应的生命周期回调
//还有一种ON_ANY类型，表示可以匹配Activity 的任何生命周期回调
public class MyObserver implements LifecycleObserver {
   //提供生命周期对象，让使用者主动获取当前的生命周期状态
   //获取当前生命周期：lifecycle.getCurrentState()
   public Lifecycle lifecycle;

   public MyObserver(Lifecycle lifecycle){
      this.lifecycle = lifecycle;
   }

   /*注册onStart()监听*/
   //在activity的onStart()触发的时候执行
   @OnLifecycleEvent(Lifecycle.Event.ON_START)
   public void activityStart(){
      Log.d("MyObserver","activityStart");
   }

   /*注册onStop()监听*/
   //在activity的onStop()触发的时候执行
   @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
   public void activityStop(){
      Log.d("boge","activityStop");
   }
}
