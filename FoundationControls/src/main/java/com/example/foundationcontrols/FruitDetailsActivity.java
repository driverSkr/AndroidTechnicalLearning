package com.example.foundationcontrols;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/*
* 水果的详情展示界面
* */
public class FruitDetailsActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar);
        ImageView fruitImageView = findViewById(R.id.fruitImageView);
        TextView fruitContentText = findViewById(R.id.fruitContentText);

        //通过Intent 获取了传入的水果名和水果图片的资源id
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID,0);

        //将它作为ActionBar 显示
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            //启用Home 按钮。由于Home 按钮的默认图标就是一个返回箭头，这正是我们所期望的，因此就不用额外设置别的图标了
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //将水果名设置成当前界面的标题
        collapsingToolbar.setTitle(fruitName);
        //使用Glide加载传入的水果图片，并设置到标题栏的ImageView上面
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        //填充水果的内容详情
        fruitContentText.setText(generateFruitContent(fruitName));
    }

    //将水果名循环拼接500 次，从而生成了一个比较长的字符串
    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    //处理了Home 按钮的点击事件，当点击这个按钮时，就调用finish()方法关闭当前的Activity ，从而返回上一个Activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}