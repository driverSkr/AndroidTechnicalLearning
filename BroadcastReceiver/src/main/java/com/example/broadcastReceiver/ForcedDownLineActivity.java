package com.example.broadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

//强制下线
public class ForcedDownLineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forced_down_line);

        findViewById(R.id.forceOffline).setOnClickListener(v -> {
            /*用于通知程序强制用户下线的*/
            /*也就是说，强制用户下线的逻辑并不是写在MainActivity 里的，而是应该写在接收这条广播的BroadcastReceiver 里。
            这样强制下线的功能就不会依附于任何界面了，不管是在程序的任何地方，只要发出这样一条广播，就可以完成强制下线的操作了*/
            Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
            sendBroadcast(intent);
        });
    }
}