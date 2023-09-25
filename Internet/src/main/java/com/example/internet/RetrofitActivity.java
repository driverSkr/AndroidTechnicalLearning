package com.example.internet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.internet.entity.App;
import com.example.internet.interfaces.AppService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* 使用Retrofit进行网络请求（实际是okhttp），并使用自带的Gson解析数据
*******当发起请求的时候，Retrofit 会自动在内部开启子线程，当数据回调到Callback中之后，Retrofit 又会自动切换回主线程，整个操作过程中我们都不用考虑线程切换问题******
* */
public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        findViewById(R.id.getAppDataBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //通过网络请求获得数据并解析json数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3/")//指定所有Retrofit 请求的根路径
                //用于指定Retrofit 在解析数据时所使用的转换库，这里指定成GsonConverterFactory
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建一个该接口的动态代理对象
        //有了动态代理对象之后，我们就可以随意调用接口中定义的所有方法，而Retrofit 会自动执行具体的处理
        AppService appService = retrofit.create(AppService.class);
        Call<List<App>> call = appService.getAppData();
        //Retrofit 就会根据注解中配置的服务器接口地址去进行网络请求了，服务器响应的数据会回调到enqueue()方法中传入的Callback实现里面
        //当发起请求的时候，Retrofit 会自动在内部开启子线程，当数据回调到Callback中之后，Retrofit 又会自动切换回主线程，整个操作过程中我们都不用考虑线程切换问题
        call.enqueue(new Callback<List<App>>() {
            @Override
            public void onResponse(Call<List<App>> call, Response<List<App>> response) {
                //调用response.body()方法将会得到Retrofit 解析后的对象
                List<App> list = response.body();
                if (list != null){
                    for (App app : list) {
                        Log.d("boge","id is " + app.getId());
                        Log.d("boge","name is " + app.getName());
                        Log.d("boge","Version is " + app.getVersion());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<App>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}