package com.example.jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_ViewModel).setOnClickListener(this);
        findViewById(R.id.btn_ViewModel_parameters).setOnClickListener(this);
        findViewById(R.id.btn_Lifecycles).setOnClickListener(this);
        findViewById(R.id.btn_livedata).setOnClickListener(this);
        findViewById(R.id.btn_room).setOnClickListener(this);
        findViewById(R.id.btn_WorkManager).setOnClickListener(this);
        findViewById(R.id.btn_WorkManager_complex).setOnClickListener(this);
        findViewById(R.id.flow_basic).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ViewModel:
                startActivity(new Intent(this,FoundationViewModelActivity.class));
                break;
            case R.id.btn_ViewModel_parameters:
                startActivity(new Intent(this,ParametersViewModelActivity.class));
                break;
            case R.id.btn_Lifecycles:
                startActivity(new Intent(this,LifecycleActivity.class));
                break;
            case R.id.btn_livedata:
                startActivity(new Intent(this,LiveDataViewModelActivity.class));
                break;
            case R.id.btn_room:
                startActivity(new Intent(this,RoomActivity.class));
                break;
            case R.id.btn_WorkManager:
                startActivity(new Intent(this,WorkManagerActivity.class));
                break;
            case R.id.btn_WorkManager_complex:
                startActivity(new Intent(this,WorkManagerComplexActivity.class));
                break;
            case R.id.flow_basic:
                startActivity(new Intent(this,FlowBasicActivity.class));
                break;
        }
    }
}