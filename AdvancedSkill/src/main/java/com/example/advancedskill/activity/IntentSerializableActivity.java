package com.example.advancedskill.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.advancedskill.R;
import com.example.advancedskill.entity.Person;
import com.example.advancedskill.test.SecondActivity;

/*Intent来传递自定义对象：Serializable实现方式*/
/*Serializable 的方式较为简单，但由于会把整个对象进行序列化，因此效率会比Parcelable方式低一些，所以在通
常情况下，还是更加推荐使用Parcelable的方式来实现Intent传递对象的功能*/
//putExtra()方法中所支持的数据类型是有限的，虽然常用的一些数据类型是支持的，但是当你想去传递一些自定义对象的时候，就会发现无从下手
//Serializable 是序列化的意思，表示将一个对象转换成可存储或可传输的状态。序列化后的对象可以在网络上进行传输，也可以存储到本地
//至于序列化的方法非常简单，只需要让一个类去实现Serializable这个接口就可以了
public class IntentSerializableActivity extends AppCompatActivity {

    public static final String way = "Serializable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_serializable);

        findViewById(R.id.btn_click).setOnClickListener(v -> {
            Person person = new Person("Tom",20);
            Intent intent = new Intent(this, SecondActivity.class);
            //由于Person类实现了Serializable接口，所以才可以这样写
            intent.putExtra("person_data",person);
            intent.putExtra("way","Serializable");
            startActivity(intent);
        });
    }
}