package com.example.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

//另外需要注意，每一个Service都需要在AndroidManifest.xml 文件中进行注册才能生效
/*不知道你有没有发现，这是Android 四大组件共有的特点*/
public class MyService extends Service {

    private final IBinder mBinder = new DownloadBinder();

    public static class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService", "startDownload executed");
        }
        public int getProgress(){
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }

    public MyService() {
    }

    //会在Service创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");
    }

    //会在每次Service启动的时候调用
    /*如果我们希望Service一旦启动就立刻去执行某个动作，就可以将逻辑写在onStartCommand()方法里*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    //会在Service销毁的时候调用
    /*而当Service销毁时，我们又应该在onDestroy()方法中回收那些不再使用的资源*/
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}