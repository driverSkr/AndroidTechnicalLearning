package com.example.foundationcontrols;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foundationcontrols.adapter.RecyclerGridAdapter;
import com.example.foundationcontrols.entity.NewsInfo;
import com.example.foundationcontrols.widget.SpacesDecoration;

//循环视图设置网格布局管理器代码实例
public class RecyclerGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid);

        initRecyclerGrid(); // 初始化网格布局的循环视图
    }

    // 初始化网格布局的循环视图
    private void initRecyclerGrid() {
        // 从布局文件中获取名叫rv_grid的循环视图
        RecyclerView rv_grid = findViewById(R.id.rv_grid);
        // 创建一个网格布局管理器（每行5列）
        GridLayoutManager manager = new GridLayoutManager(this,5);
        rv_grid.setLayoutManager(manager); // 设置循环视图的布局管理器
        // 构建一个市场列表的网格适配器
        RecyclerGridAdapter adapter = new RecyclerGridAdapter(this, NewsInfo.getDefaultGrid());
        adapter.setOnItemClickListener(adapter); // 设置网格列表的点击监听器
        adapter.setOnItemLongClickListener(adapter); // 设置网格列表的长按监听器
        rv_grid.setAdapter(adapter);// 设置循环视图的网格适配器
        rv_grid.setItemAnimator(new DefaultItemAnimator());  // 设置循环视图的动画效果
        rv_grid.addItemDecoration(new SpacesDecoration(1));  // 设置循环视图的空白装饰
    }
}