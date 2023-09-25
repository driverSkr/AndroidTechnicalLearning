package com.example.jetpack;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpack.ViewModel.LiveDataMapViewModel;
import com.example.jetpack.ViewModel.LiveDataSwitchMapViewModel;
import com.example.jetpack.ViewModel.LiveDataViewModel;
import com.example.jetpack.entity.User;
import com.example.jetpack.factory.LiveDataViewModelFactory;

import java.util.Random;

/*
* 向ViewModel传递参数：借助ViewModelProvider.Factory就可以实现了
* */
//在屏幕旋转的时候不会丢失数据
//借助SharedPreferences，即使在退出程序后又重新打开的情况下，数据仍然不会丢失
public class LiveDataViewModelActivity extends AppCompatActivity {

    //绝对不可以直接去创建ViewModel 的实例，而是一定要通过ViewModelProvider 来获取ViewModel 的实例
    LiveDataViewModel viewModel = null;
    LiveDataSwitchMapViewModel switchMapViewModel = null;
    private TextView infoText;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_view_model);

        sp = getPreferences(Context.MODE_PRIVATE);
        //读取之前保存的计数值，如果没有读到的话，就使用0作为默认值
        int countReserved = sp.getInt("count_reserved", 0);

        //将读取到的计数值传给了MainViewModelFactory的构造函数
        viewModel = new ViewModelProvider(this, new LiveDataViewModelFactory(countReserved)).get(LiveDataViewModel.class);
        infoText = findViewById(R.id.infoText);
        findViewById(R.id.plusOneBtn).setOnClickListener(v -> {
            viewModel.plusOne();
        });

        findViewById(R.id.clearBtn).setOnClickListener(v -> {
            viewModel.clear();
        });

        /*观察数据的变化*/
        //经过对MainViewModel 的改造，现在counter变量已经变成了一个LiveData对象，任何LiveData对象都可以调用它的observe()方法来观察数据的变化
        /*observe()方法接收两个参数：
        第一个参数是一个LifecycleOwner对象，Activity 本身就是一个LifecycleOwner对象，因此直接传this就好；
        第二个参数是一个Observer接口，当counter中包含的数据发生变化时，就会回调到这里，
        因此我们在这里将最新的计数更新到界面上即可*/
        viewModel.getCounter().observe(this, integer -> infoText.setText(integer+""));

    /*switchMap():观察ViewModel外部的LiveData数据*/
        switchMapViewModel = new ViewModelProvider(this).get(LiveDataSwitchMapViewModel.class);
        findViewById(R.id.getUserBtn).setOnClickListener(v -> {
            String userId = String.valueOf(new Random().nextInt(10001));
            //但是这个方法现在不会有任何返回值了
            switchMapViewModel.getUser(userId);
        });
        //等数据获取完成之后，可观察user对象的observe()方法将会得到通知，我们在这里将获取的用户名显示到界面上
        switchMapViewModel.user.observe(this, user -> infoText.setText(user.getFirstName()+""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在程序退出后，数据保存到共享参数中
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count_reserved", viewModel.getCounter().getValue() != null ? viewModel.getCounter().getValue():0);
        editor.apply();
    }
}