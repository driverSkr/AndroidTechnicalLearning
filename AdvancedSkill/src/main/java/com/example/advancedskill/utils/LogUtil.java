package com.example.advancedskill.utils;

import android.util.Log;

//自定义的日志工具
//只需要在开发阶段将level指定成VERBOSE，当项目正式上线的时候将level指定成ERROR就可以统一屏蔽日志打印
public class LogUtil {

    public static final String tag = "boge";

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;

    //只有当level的值小于或等于对应日志级别值的时候，才会将日志打印出来
    //需要通过修改level变量的值，就可以自由地控制日志的打印行为。比如让level等于VERBOSE就可以把所有的日志都打印出来，让level等于ERROR就可以只打印程序的错误日志
    private static int level = VERBOSE;

    public static void v(String tag,String msg){
        if (level <= VERBOSE){
            Log.v(tag,msg);
        }
    }

    public static void d(String tag,String msg){
        if (level <= DEBUG){
            Log.d(tag,msg);
        }
    }

    public static void i(String tag,String msg){
        if (level <= INFO){
            Log.i(tag,msg);
        }
    }

    public static void w(String tag,String msg){
        if (level <= WARN){
            Log.w(tag,msg);
        }
    }

    public static void e(String tag,String msg){
        if (level <= ERROR){
            Log.e(tag,msg);
        }
    }
}
