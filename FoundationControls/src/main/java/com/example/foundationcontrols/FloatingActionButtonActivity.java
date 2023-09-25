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
import com.google.android.material.snackbar.Snackbar;

/*
* 悬浮按钮:FloatingActionButton是Material库中提供的一个控件，这个控件可以帮助我们比较轻松地实现悬浮按钮的效果
* 可交互提示Snackbar:并不是Toast 的替代品，它们有着不同的应用场景。Toast 的作用是告诉用户现在发生了什么事情，但用户只能被动接收这个事情，因为没有什么办法能让用户进行选择。而Snackbar 则在这方面进行了扩展，它允许在提示中加入一个可交互按钮，当用户点击按钮的时候，可以执行一些额外的逻辑操作
* */
public class FloatingActionButtonActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
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

/***********************************************悬浮按钮:FloatingActionButton***********************************************************************************/
/***********************************************可交互提示Snackbar**********************************************************************************************/
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
                                Toast.makeText(FloatingActionButtonActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
/*****************************************************************************************************************************************************************/
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