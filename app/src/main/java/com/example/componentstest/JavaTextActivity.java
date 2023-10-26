package com.example.componentstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.componentstest.entity.Music;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_text);

        List<Music> list = new ArrayList<>();
        Set<String> set = new HashSet<>();

        Music music = new Music(2,"sdsdf");

        list.add(new Music(1,"义勇军进行曲"));
        list.add(new Music(1,"义勇军进行曲"));
        list.add(new Music(3,"义勇军进行曲3"));
        list.add(new Music(4,"义勇军进行曲4"));
        list.add(music);

        Log.d("boge",list.get(0).toString());
    }
}