package com.example.foundationcontrols;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
* MaterialCardView卡片式布局
* 使用AppBarLayout解决toolbar被遮挡问题
* */
public class MaterialCardViewActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_card_view);
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
                                Toast.makeText(MaterialCardViewActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
/*****************************************************************************************************************************************************************/
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits(){
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
    /*****************************************************************************************************************************************************************/
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