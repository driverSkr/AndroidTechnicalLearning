package com.example.foundationcontrols;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.foundationcontrols.adapter.TabPagerAdapter;

//结合 RadioGroup 和 ViewPager 自定义底部标签栏
public class TabPagerActivity extends AppCompatActivity {
    private ViewPager vp_content; // 声明一个翻页视图对象
    private RadioGroup rg_tab_bar; // 声明一个单选组对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pager);

        vp_content = findViewById(R.id.vp_content); // 从布局文件获取翻页视图
        // 构建一个翻页适配器
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        vp_content.setAdapter(adapter);// 设置翻页视图的适配器
        // 给翻页视图添加页面变更监听器
        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //选中指定位置的单选按钮
                rg_tab_bar.check(rg_tab_bar.getChildAt(position).getId());
            }
        });
        rg_tab_bar = findViewById(R.id.rg_tab_bar); // 从布局文件获取单选组
        // 设置单选组的选中监听器
        rg_tab_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int pos = 0; pos < rg_tab_bar.getChildCount(); pos++){
                    // 获得指定位置的单选按钮
                    RadioButton tab = (RadioButton) rg_tab_bar.getChildAt(pos);
                    if (tab.getId() == checkedId){ // 正是当前选中的按钮
                        vp_content.setCurrentItem(pos); // 设置翻页视图显示第几页
                    }
                }
            }
        });
    }
}