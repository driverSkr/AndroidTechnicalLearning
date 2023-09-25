package com.example.foundationcontrols;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foundationcontrols.adapter.FruitRecyclerAdapter;
import com.example.foundationcontrols.entity.Fruit;

import java.util.ArrayList;
import java.util.Random;

/*
* RecyclerView实现瀑布流布局
* */
//除了LinearLayoutManager之外，RecyclerView还给我们提供了GridLayoutManager和StaggeredGridLayoutManager这两种内置的布局排列方式
//GridLayoutManager可以用于实现网格布局
//StaggeredGridLayoutManager可以用于实现瀑布流布局
public class RecyclerStaggeredGridViewActivity extends AppCompatActivity {

    private ArrayList<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_staggered_grid_view);

        initFruits();//初始化水果数据
//-----------------------设置成瀑布布局-----------------------------------------------
        //第一个参数用于指定布局的列数，传入3表示会把布局分为3列；
        // 第二个参数用于指定布局的排列方向，传入StaggeredGridLayoutManager.VERTICAL表示会让布局纵向排列
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//-------------------------------------------------------------------------------------
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //用于指定RecyclerView的布局方式
        recyclerView.setLayoutManager(layoutManager);

        //完成适配器设置
        FruitRecyclerAdapter adapter = new FruitRecyclerAdapter(fruitList);
        recyclerView.setAdapter(adapter);
//--------------------这样RecyclerView和数据之间的关联就建立完成了------------------------
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            fruitList.add(new Fruit(getRandomLengthString("Apple"), R.drawable.apple_pic));
            fruitList.add(new Fruit(getRandomLengthString("Banana"), R.drawable.banana_pic));
            fruitList.add(new Fruit(getRandomLengthString("Orange"), R.drawable.orange_pic));
            fruitList.add(new Fruit(getRandomLengthString("Watermelon"), R.drawable.watermelon_pic));
            fruitList.add(new Fruit(getRandomLengthString("Pear"), R.drawable.pear_pic));
            fruitList.add(new Fruit(getRandomLengthString("Grape"), R.drawable.grape_pic));
            fruitList.add(new Fruit(getRandomLengthString("Pineapple"), R.drawable.pineapple_pic));
            fruitList.add(new Fruit(getRandomLengthString("Strawberry"), R.drawable.strawberry_pic));
            fruitList.add(new Fruit(getRandomLengthString("Cherry"), R.drawable.cherry_pic));
            fruitList.add(new Fruit(getRandomLengthString("Mango"), R.drawable.mango_pic));
        }
    }

    //由于瀑布流布局需要各个子项的高度不一致才能看出明显的效果
    //将参数中传入的字符串随机重复几遍。在initFruits()方法中，每个水果的名字都改成调用getRandomLengthString()这个方法来生成，这样就能保证各水果名字的长短差距比较大，子项的高度也就各不相同了
    private String getRandomLengthString(String str) {
        int n = new Random().nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(str);
        }
        return builder.toString();
    }
}