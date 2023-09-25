package com.example.jetpack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.jetpack.ViewModel.FoundationViewModel;

/*
* ViewModel的基本用法
* */
//一定要通过ViewModelProvider 来获取ViewModel 的实例
public class FoundationViewModelActivity extends AppCompatActivity {

    //绝对不可以直接去创建ViewModel 的实例，而是一定要通过ViewModelProvider 来获取ViewModel 的实例
    FoundationViewModel viewModel = null;
    private TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_view_model);

        viewModel = new ViewModelProvider(this).get(FoundationViewModel.class);
        infoText = findViewById(R.id.infoText);
        findViewById(R.id.plusOneBtn).setOnClickListener(v -> {
            viewModel.counter++;
            //点击后显示
            refreshCounter();
        });
        //为了在activity刚创建时，显示数字
        refreshCounter();
    }

    private void refreshCounter() {
        infoText.setText(viewModel.counter+"");
    }
}