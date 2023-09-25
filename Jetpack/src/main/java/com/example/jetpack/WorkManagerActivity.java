package com.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import com.example.jetpack.worker.SimpleWorker;

import java.util.concurrent.TimeUnit;

/*
* WorkManager的基本用法
* */
//WorkManager 很适合用于处理一些要求定时执行的任务，它可以根据操作系统的版本自动选择底层是使用AlarmManager实现还是JobScheduler实现，从而降低了我们的使用成本。另外，它还支持周期性任务、链式任务处理等功能，是一个非常强大的工具
//WorkManager 只是一个处理定时任务的工具，它可以保证即使在应用退出甚至手机重启的情况下，之前注册的任务仍然将会得到执行，因此WorkManager 很适合用于执行一些定期和服务器进行交互的任务，比如周期性地同步数据，等等
//使用WorkManager 注册的周期性任务不能保证一定会准时执行，这并不是bug ，而是系统为了减少电量消耗，可能会将触发时间临近的几个任务放在一起执行，这样可以大幅度地减少CPU被唤醒的次数，从而有效延长电池的使用时间
public class WorkManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);

        //2.配置该后台任务的运行条件和约束信息，并构建后台任务请求；
        //OneTimeWorkRequest.Builder是WorkRequest.Builder的子类，用于构建单次运行的后台任务请求
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SimpleWorker.class).build();
        //PeriodicWorkRequest.Builder同样是WorkRequest.Builder的子类，可用于构建周期性运行的后台任务请求，
        // 但是为了降低设备性能消耗,构造函数中传入的运行周期间隔不能短于15 分钟
        //PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(SimpleWorker.class, 15, TimeUnit.MINUTES).build();

        //3.将该后台任务请求传入WorkManager 的enqueue()方法中，系统会在合适的时间运行
        WorkManager.getInstance(this).enqueue(request);
    }
}