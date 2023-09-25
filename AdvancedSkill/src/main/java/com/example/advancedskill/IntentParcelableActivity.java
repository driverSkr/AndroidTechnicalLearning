package com.example.advancedskill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.advancedskill.entity.User;
import com.example.advancedskill.test.SecondActivity;

/*Intent来传递自定义对象：Parcelable实现方式*/
//不同于将对象进行序列化，Parcelable方式的实现原理是将一个完整的对象进行分解，而分解后的每一部分都是Intent所支持的数据类型，这样就能实现传递对象的功能了
public class IntentParcelableActivity extends AppCompatActivity {

    public static final String way = "Parcelable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_parcelable);

        findViewById(R.id.btn_click).setOnClickListener(view -> {
            User user = new User("boge",18);
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("person_data",user);
            intent.putExtra("way","Parcelable");
            startActivity(intent);
        });
    }
}