package com.example.advancedskill;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/*Android提供了一个Application类，每当应用程序启动的时候，系统就会自动将这个类进行初始化。
而我们可以定制一个自己的Application类，以便于管理程序内一些全局的状态信息，比如全局Context*/
public class MyApplication extends Application {

   //获取Context并非是那么容易的一件事，有时候还是挺伤脑筋的,设置全局Context
   //将Context设置成静态变量很容易会产生内存泄漏的问题，所以这是一种有风险的做法
   //但是由于这里获取的不是Activity 或Service 中的Context，而是Application 中的Context，它全局只会存在一份实例，并且在整个应用程序的生命周期内都不会回收，因此是不存在内存泄漏风险的
   @SuppressLint("StaticFieldLeak")//让Android Studio 忽略上述警告提示
   public static Context context = null;

   @Override
   public void onCreate() {
      super.onCreate();
      context = getApplicationContext();
   }
}
