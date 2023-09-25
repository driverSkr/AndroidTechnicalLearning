package com.example.componentstest.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/*检查权限、检查权限请求结果*/
public class PermissionUtil {

    //检查多个权限，返回true表示已完全启用权限，返回false表示未完全启用权限
    public static boolean checkPermission(Activity act,String[] permissions,int requestCode){
        //Android 6.0 之后开始采用动态权限管理
        int check = PackageManager.PERMISSION_GRANTED;
        for (String permission : permissions){
            //【返回：0:授权;-1:未授权】
            check = ContextCompat.checkSelfPermission(act,permission);
            if (check != PackageManager.PERMISSION_GRANTED){
                //只要有一个权限没获得，直接跳出验证
                break;
            }
        }
        //未开启该权限，则请求系统弹窗，好让用户选择是否立即开启权限【如果连续2次被拒绝，则不能请求，默认拒绝】
        if (check != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(act,permissions,requestCode);
            return false;
        }
        return true;
    }

    //检查权限结果数组，返回true表示都已经获得授权。返回false表示至少有一个未获得授权
    public static boolean checkGrant(int[] grantResults) {
        if (grantResults != null){
            //遍历权限结果数组中的每条选择结果
            for (int grant : grantResults){
                //未获得授权
                if (grant != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
