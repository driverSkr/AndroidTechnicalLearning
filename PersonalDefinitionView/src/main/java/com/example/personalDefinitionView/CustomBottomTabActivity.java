package com.example.personalDefinitionView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.personalDefinitionView.fragment.DiscoverFragment;
import com.example.personalDefinitionView.fragment.HomeFragment;
import com.example.personalDefinitionView.fragment.MineFragment;
import com.example.personalDefinitionView.fragment.NearbyFragment;
import com.example.personalDefinitionView.fragment.OrderFragment;
import com.example.personalDefinitionView.widget.CustomBottomTabWidget;

import java.util.ArrayList;
import java.util.List;

public class CustomBottomTabActivity extends AppCompatActivity {

    List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bottom_tab);

        addFragments();
        CustomBottomTabWidget tabWidget = findViewById(R.id.tabWidget);
        tabWidget.init(getSupportFragmentManager(),fragmentList);
    }

    private void addFragments(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NearbyFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new MineFragment());
    }
}