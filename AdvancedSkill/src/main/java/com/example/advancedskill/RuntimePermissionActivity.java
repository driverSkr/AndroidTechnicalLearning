package com.example.advancedskill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

//运行时申请权限
/*Android 开发团队在Android 6.0 系统中引入了运行时权限这个功能，从而更好地保护了用户的安全和隐私*/
/*当然，并不是所有权限都需要在运行时申请
*   普通权限:指的是那些不会直接威胁到用户的安全和隐私的权限，对于这部分权限申请，系统会自动帮我们进行授权，不需要用户手动操作
*   危险权限:则表示那些可能会触及用户隐私或者对设备安全性造成影响的权限，如获取设备联系人信息、定位设备的地理位置等，对于这部分权限申请，必须由用户手动授权才可以，否则程序就无法使用相应的功能*/
public class RuntimePermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);

        findViewById(R.id.makeCall).setOnClickListener(view -> {
            //先判断用户是不是已经给过我们授权了
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                //如果没有授权的话，则需要调用ActivityCompat.requestPermissions()方法向用户申请授权
                /*requestPermissions()方法接收3个参数：
                第一个参数要求是Activity 的实例；
                第二个参数是一个String数组，我们把要申请的权限名放在数组中即可；
                第三个参数是请求码，只要是唯一值就可以了，这里传入了1*/
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
            }else {
                //已经授权的话就直接执行拨打电话的逻辑操作
                call();
            }
        });
    }

    //系统会弹出一个权限申请的对话框，用户可以选择同意或拒绝我们的权限申请。不论是哪种结果，最终都会回调到onRequestPermissionsResult()方法中
    //授权的结果则会封装在grantResults参数当中
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                call();
            }else {
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }
}