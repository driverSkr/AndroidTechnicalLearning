package com.example.componentstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.componentstest.adapter.MusicListAdapter;
import com.example.componentstest.entity.Music;

import java.util.List;

public class RecycleAddDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Music> defaultMusicList;
    private RecyclerView rv_content;
    private MusicListAdapter musicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_add_delete);

        defaultMusicList = Music.getDefaultMusic();
        musicListAdapter = new MusicListAdapter(this,defaultMusicList);

        rv_content = findViewById(R.id.rv_content);
        // 创建一个垂直方向的线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(
                this, RecyclerView.VERTICAL, false);
        rv_content.setLayoutManager(manager);
        rv_content.setAdapter(musicListAdapter);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                musicListAdapter.add();
                break;
            case R.id.btn_delete:
                musicListAdapter.delete();
                break;
        }
    }
}