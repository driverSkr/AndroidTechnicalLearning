package com.example.foundationcontrols;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.foundationcontrols.adapter.FruitAdapter;
import com.example.foundationcontrols.entity.Fruit;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* SwipeRefreshLayout:下拉刷新
* (用于实现下拉刷新功能的核心类，我们把想要实现下拉刷新功能的控件放置到SwipeRefreshLayout 中，就可以迅速让这个控件支持下拉刷新)
* */
public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Fruit[] fruits = {new Fruit("Apple",R.drawable.apple),
            new Fruit("Banana",R.drawable.banana),
            new Fruit("Orange",R.drawable.orange),
            new Fruit("Watermelon",R.drawable.watermelon),
            new Fruit("Pear",R.drawable.pear),
            new Fruit("Grape",R.drawable.grape),
            new Fruit("Pineapple",R.drawable.pineapple),
            new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Cherry",R.drawable.cherry),
            new Fruit("Mango",R.drawable.mango)};

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //这样我们就做到既使用了Toolbar，又让它的外观与功能都和ActionBar一致了
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navView = findViewById(R.id.navView);

        //调用getSupportActionBar()方法得到了ActionBar的实例，虽然这个ActionBar的具体实现是由Toolbar来完成的
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            //让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置一个导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //将Call菜单项设置为默认选中
        navView.setCheckedItem(R.id.navCall);
        //设置一个菜单项选中事件的监听器，当用户点击了任意菜单项时，就会回调到传入的Lambda表达式当中，我们可以在这里编写具体的逻辑处理
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //将滑动菜单关闭，并返回true表示此事件已被处理
                drawerLayout.closeDrawers();
                return true;
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个参数需要传入一个View ，只要是当前界面布局的任意一个View 都可以，
                // Snackbar 会使用这个View 自动查找最外层的布局，用于展示提示信息
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT)
                        //调用了一个setAction()方法来设置一个动作，从而让Snackbar 不仅仅是一个提示，而是可以和用户进行交互的
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SwipeRefreshLayoutActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
//---------------------------swipeRefresh下拉刷新代码-------------------------------------------------------------------------
        swipeRefresh = findViewById(R.id.swipeRefresh);
        //设置下拉刷新进度条的颜色
        swipeRefresh.setColorSchemeResources(R.color.purple_500);
        //设置一个下拉刷新的监听器
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //通常情况下，当触发了下拉刷新事件，应该是去网络上请求最新的数据，然后再将这些数据展示出来
                //这里简单起见，我们就不和网络进行交互了，进行本地刷新操作
                refreshFruits(adapter);
            }
        });

    }
    private void refreshFruits(FruitAdapter adapter){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //先是开启了一个线程，然后将线程沉睡两秒钟。之所以这么做，是因为本地刷新操作速度非常快，
                    // 如果不将线程沉睡的话，刷新立刻就结束了，从而看不到刷新的过程
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                //沉睡结束之后，这里使用了runOnUiThread()方法将线程切换回主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //调用initFruits()方法重新生成数据
                        initFruits();
                        //通知数据发生了变化
                        adapter.notifyDataSetChanged();
                        //表示刷新事件结束，并隐藏刷新进度条
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
//-------------------------------------------------------------------------------------------------------
    private void initFruits(){
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //处理各个按钮的点击事件
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            //Home按钮的id永远都是android.R.id.home
            case android.R.id.home:
                //将滑动菜单展示出来
                //openDrawer()方法要求传入一个Gravity参数，为了保证这里的行为和XML中定义的一致，我们传入了GravityCompat.START
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}