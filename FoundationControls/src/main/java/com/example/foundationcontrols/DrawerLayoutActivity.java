package com.example.foundationcontrols;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

/*
* DrawerLayout滑动菜单:首先它是一个布局，在布局中允许放入两个直接子控件：
                    * 第一个子控件是主屏幕中显示的内容，
                    * 第二个子控件是滑动菜单中显示的内容
* */
public class DrawerLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
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