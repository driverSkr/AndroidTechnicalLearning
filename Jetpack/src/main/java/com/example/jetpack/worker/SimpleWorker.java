package com.example.jetpack.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/*
* WorkManager的基本用法
* */
//1.定义一个后台任务
public class SimpleWorker extends Worker {

    public SimpleWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    //写具体的后台任务逻辑
    //doWork()方法不会运行在主线程当中，因此你可以放心地在这里执行耗时逻辑，
    @NonNull
    @Override
    //要求返回一个Result对象，用于表示任务的运行结果
    // 成功就返回Result.success()，失败就返回Result.failure()
    public Result doWork() {
        Log.d("boge","运行一个后台任务");
        return null;
    }
}
