package com.example.personalDefinitionView.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.personalDefinitionView.R;

//自定义View：实现title视图
public class TitleLayout extends LinearLayout {
    //在XML文件中添加视图节点时，会调用这个构造方法
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        //第一个参数是要加载的布局文件的id
        //第二个参数是给加载好的布局再添加一个父布局
        LayoutInflater.from(context).inflate(R.layout.title,this);
        findViewById(R.id.titleBack).setOnClickListener(v -> {
            Activity activity = (Activity) context;
            activity.finish();
        });
        findViewById(R.id.titleEdit).setOnClickListener(v -> {
            Toast.makeText(context,"You clicked Edit button",Toast.LENGTH_SHORT).show();
        });
    }
}
