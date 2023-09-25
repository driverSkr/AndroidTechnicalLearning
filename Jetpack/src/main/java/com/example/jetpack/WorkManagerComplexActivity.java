package com.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;

import com.example.jetpack.worker.SimpleWorker;

import java.util.concurrent.TimeUnit;

/*使用WorkManager 处理复杂的任务*/
//介绍的WorkManager 的所有功能，在国产手机上都有可能得不到正确的运行。这是因为绝大多数的国产手机厂商在进行Android系统定制的时候会增加一个一键关闭的功能，允许用户一键杀死所有非白名单的应用程序。
// 而被杀死的应用程序既无法接收广播，也无法运行WorkManager 的后台任务。这个功能虽然与Android原生系统的设计理念并不相符，但是我们也没有什么解决办法。或许就是因为有太多恶意应用总是想要无限占用后台，国产手机厂商才增加了这个功能吧。因此，这里给你的建议就是，WorkManager 可以用，但是千万别依赖它去实现什么核心功能，因为它在国产手机上可能会非常不稳定。
public class WorkManagerComplexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_complex);

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(SimpleWorker.class)
                /*让后台任务在指定的延迟时间后运行*/
                //表示我们希望让SimpleWorker这个后台任务在5分钟后运行。你可以自由选择时间的单位，毫秒、秒、分钟、小时、天都可以
                .setInitialDelay(5, TimeUnit.MINUTES)
                /*给后台任务请求添加标签*/
                .addTag("simple")
                /*如果后台任务的doWork()方法中返回了Result.retry()，那么是可以结合setBackoffCriteria()方法来重新执行任务的*/
                //第二个和第三个参数用于指定在多久之后重新执行任务，时间最短不能少于10 秒钟
                //第一个参数则用于指定如果任务再次执行失败，下次重试的时间应该以什么样的形式延迟
                //第一个参数的可选值有两种，分别是LINEAR和EXPONENTIAL，前者代表下次重试时间以线性的方式延迟，后者代表下次重试时间以指数的方式延迟
                .setBackoffCriteria(BackoffPolicy.LINEAR,10,TimeUnit.SECONDS)
                .build();

//--------------------------------添加了标签有什么好处呢？----------------------------------------------
        // 最主要的一个功能就是我们可以通过标签来取消后台任务请求：
        WorkManager.getInstance(this).cancelAllWorkByTag("simple");
        //当然，即使没有标签，也可以通过id来取消后台任务请求：
        //但是，使用id只能取消单个后台任务请求，而使用标签的话，则可以将同一标签名的所有后台任务请求全部取消，这个功能在逻辑复杂的场景下尤其有用
        WorkManager.getInstance(this).cancelWorkById(request.getId());
        //除此之外，我们也可以使用如下代码来一次性取消所有后台任务请求：
        WorkManager.getInstance(this).cancelAllWork();

//-----------------doWork()方法中返回Result.success()和Result.failure()又有什么作用？---------------------------------
        // 这两个返回值其实就是用于通知任务运行结果的，我们可以使用如下代码对后台任务的运行结果进行监听：
        //另外，你也可以调用getWorkInfosByTagLiveData()方法，监听同一标签名下所有后台任务请求的运行结果，用法是差不多的，这里就不再进行解释了
        WorkManager.getInstance(this)
                //传入后台任务请求的id，会返回一个LiveData对象。
                .getWorkInfoByIdLiveData(request.getId())
                //然后我们就可以调用LiveData对象的observe()方法来观察数据变化了，以此监听后台任务的运行结果
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo.getState() == WorkInfo.State.SUCCEEDED){
                            Log.d("boge","do work succeed");
                        }else if (workInfo.getState() == WorkInfo.State.FAILED){
                            Log.d("boge","do work failed");
                        }
                    }
                });

//----------------------链式任务-------------------------------------
        OneTimeWorkRequest workRequest,workRequest1,workRequest2;
        //beginWith()方法用于开启一个链式任务，
        //至于后面要接上什么样的后台任务，只需要使用then()方法来连接即可。另外WorkManager
        //还要求，必须在前一个后台任务运行成功之后，下一个后台任务才会运行。也就是说，如果某
        //个后台任务运行失败，或者被取消了，那么接下来的后台任务就都得不到运行了
       /* WorkManager.getInstance(this)
                .beginWith(workRequest)
                .then(workRequest1)
                .then(workRequest2)
                .enqueue();*/
    }
}