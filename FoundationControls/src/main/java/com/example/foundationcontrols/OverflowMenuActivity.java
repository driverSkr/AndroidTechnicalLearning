package com.example.foundationcontrols;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foundationcontrols.util.DateUtil;

//溢出菜单OverflowMenu
/*点击导航栏右边的三点图标，会弹出溢出菜单OverflowMenu，意指导航栏不够放了、溢出来了。*/
public class OverflowMenuActivity extends AppCompatActivity {
    private TextView tv_desc; // 声明一个文本视图对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_menu);

        Toolbar tl_head = findViewById(R.id.tl_head); // 从布局文件中获取名叫tl_head的工具栏
        tl_head.setTitle("溢出菜单页面"); // 设置工具栏的标题文字
        setSupportActionBar(tl_head); // 使用tl_head替换系统自带的ActionBar
        tv_desc = findViewById(R.id.tv_desc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_overflow.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); // 获取菜单项的编号
        if (id == android.R.id.home){ // 点击了工具栏左边的返回箭头
            finish(); // 结束当前页面
        } else if (id == R.id.menu_refresh) { // 点击了刷新图标
            tv_desc.setText("当前刷新时间: " + DateUtil.getNowTime());
        } else if (id == R.id.menu_about) { // 点击了关于菜单项
            Toast.makeText(this, "这个是工具栏的演示demo", Toast.LENGTH_LONG).show();
        } else if (id == R.id.menu_quit) { // 点击了退出菜单项
            finish(); // 结束当前页面
        }
        return super.onOptionsItemSelected(item);
    }
}