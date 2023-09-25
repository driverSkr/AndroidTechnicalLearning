package com.example.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.broadcastReceiver.Collector.ActivityCollector;

//创建BaseActivity类作为所有Activity 的父类
/*为什么要这样写呢？之前不都是在onCreate()和onDestroy()方法里注册和取消注册BroadcastReceiver 的吗？*/
//这是因为我们始终需要保证只有处于栈顶的Activity 才能接收到这条强制下线广播，非栈顶的Activity 不应该也没必要接收这条广播，所以写在onResume()和
//onPause()方法里就可以很好地解决这个问题，当一个Activity 失去栈顶位置时就会自动取消BroadcastReceiver 的注册
public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消注册
        unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            //将对话框设为不可取消，否则用户按一下Back 键就可以关闭对话框继续使用程序了
            builder.setCancelable(false);
            builder.setPositiveButton("OK", (dialog, which) -> {
                ActivityCollector.finishAll();//销毁所有Activity
                Intent i = new Intent(context,LoginActivity.class);
                context.startActivity(i);//重新启动LoginActivity
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
