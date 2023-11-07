package com.example.advancedskill.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import com.example.advancedskill.activity.IntentParcelableActivity;
import com.example.advancedskill.activity.IntentSerializableActivity;
import com.example.advancedskill.entity.Person;
import com.example.advancedskill.entity.User;
import com.example.advancedskill.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tv_text = findViewById(R.id.tv_text);
        String way = getIntent().getStringExtra("way");

        if (way != null){
            if (way.equals(IntentSerializableActivity.way)){
                //调用了Intent的getSerializableExtra()方法来获取通过参数传递过来的序列化对象，接着再将它向下转型成Person对象
                Person person = (Person) getIntent().getSerializableExtra("person_data");
                tv_text.setText(way +"方式："+ person.toString());
            }else if (way.equals(IntentParcelableActivity.way)){
                //调用getParcelableExtra()方法来获取传递过来的对象，其他的地方完全相同
                User user = (User)getIntent().getParcelableExtra("person_data");
                tv_text.setText(way +"方式："+ user.toString());
            }
        } else {
            tv_text.setText("第二行代码");
        }

        setCustomTaskDescription();
    }

    private void setCustomTaskDescription() {
        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription("SecondActivity",
                BitmapFactory.decodeResource(getResources(), R.drawable.zhiboba));
        setTaskDescription(taskDescription);
    }
}