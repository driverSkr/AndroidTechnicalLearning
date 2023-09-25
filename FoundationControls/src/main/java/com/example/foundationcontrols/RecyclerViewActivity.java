package com.example.foundationcontrols;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foundationcontrols.adapter.FruitRecyclerAdapter;
import com.example.foundationcontrols.adapter.FruitRecyclerClickAdapter;
import com.example.foundationcontrols.entity.Fruit;

import java.util.ArrayList;

/*
* RecyclerView 的基本用法
* */
public class RecyclerViewActivity extends AppCompatActivity {

    private ArrayList<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initFruits();//初始化水果数据
        //使用的LinearLayoutManager是线性布局的意思，可以实现和ListView类似的效果
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //用于指定RecyclerView的布局方式
        recyclerView.setLayoutManager(layoutManager);

        //完成适配器设置
        FruitRecyclerClickAdapter adapter = new FruitRecyclerClickAdapter(fruitList);
        recyclerView.setAdapter(adapter);
//--------------------这样RecyclerView和数据之间的关联就建立完成了------------------------
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            fruitList.add(new Fruit("Apple", R.drawable.apple_pic));
            fruitList.add(new Fruit("Banana", R.drawable.banana_pic));
            fruitList.add(new Fruit("Orange", R.drawable.orange_pic));
            fruitList.add(new Fruit("Watermelon", R.drawable.watermelon_pic));
            fruitList.add(new Fruit("Pear", R.drawable.pear_pic));
            fruitList.add(new Fruit("Grape", R.drawable.grape_pic));
            fruitList.add(new Fruit("Pineapple", R.drawable.pineapple_pic));
            fruitList.add(new Fruit("Strawberry", R.drawable.strawberry_pic));
            fruitList.add(new Fruit("Cherry", R.drawable.cherry_pic));
            fruitList.add(new Fruit("Mango", R.drawable.mango_pic));
        }
    }
}