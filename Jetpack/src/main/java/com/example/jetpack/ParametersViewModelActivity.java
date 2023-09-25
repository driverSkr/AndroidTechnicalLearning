package com.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jetpack.ViewModel.FoundationViewModel;
import com.example.jetpack.ViewModel.ParametersViewModel;
import com.example.jetpack.factory.ParametersViewModelFactory;

/*
* 向ViewModel传递参数：借助ViewModelProvider.Factory就可以实现了
* */
//在屏幕旋转的时候不会丢失数据
//借助SharedPreferences，即使在退出程序后又重新打开的情况下，数据仍然不会丢失
public class ParametersViewModelActivity extends AppCompatActivity {

    //绝对不可以直接去创建ViewModel 的实例，而是一定要通过ViewModelProvider 来获取ViewModel 的实例
    ParametersViewModel viewModel = null;
    private TextView infoText;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters_view_model);

        sp = getPreferences(Context.MODE_PRIVATE);
        //读取之前保存的计数值，如果没有读到的话，就使用0作为默认值
        int countReserved = sp.getInt("count_reserved", 0);

        //将读取到的计数值传给了MainViewModelFactory的构造函数
        viewModel = new ViewModelProvider(this, new ParametersViewModelFactory(countReserved)).get(ParametersViewModel.class);
        infoText = findViewById(R.id.infoText);
        findViewById(R.id.plusOneBtn).setOnClickListener(v -> {
            viewModel.counter++;
            //点击后显示
            refreshCounter();
        });
        //为了在activity刚创建时，显示数字
        refreshCounter();

        findViewById(R.id.clearBtn).setOnClickListener(v -> {
            viewModel.counter = 0;
            refreshCounter();
        });
        refreshCounter();
    }

    private void refreshCounter() {
        infoText.setText(viewModel.counter+"");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在程序退出后，数据保存到共享参数中
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count_reserved", viewModel.counter);
        editor.apply();
    }
}