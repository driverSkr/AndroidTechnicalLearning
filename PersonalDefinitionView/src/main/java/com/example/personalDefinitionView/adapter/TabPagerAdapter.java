package com.example.personalDefinitionView.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.personalDefinitionView.fragment.DiscoverFragment;
import com.example.personalDefinitionView.fragment.HomeFragment;
import com.example.personalDefinitionView.fragment.MineFragment;
import com.example.personalDefinitionView.fragment.NearbyFragment;
import com.example.personalDefinitionView.fragment.OrderFragment;

import java.util.List;

//FragmentPagerAdapter继承自PagerAdapter
public class TabPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;

    // 碎片页适配器的构造方法，传入碎片管理器
    public TabPagerAdapter(@NonNull FragmentManager fm , List<Fragment> fragments) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    // 获取指定位置的碎片Fragment
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return fragments.get(0);  //返回第一个碎片
        } else if (position == 1) {
            return fragments.get(1);
        } else if (position == 2) {
            return fragments.get(2);  // 返回第三个碎片
        } else if (position == 3){
            return fragments.get(3);
        } else return fragments.get(4);
    }

    // 获取碎片Fragment的个数
    @Override
    public int getCount() {
        return 5;
    }
}
