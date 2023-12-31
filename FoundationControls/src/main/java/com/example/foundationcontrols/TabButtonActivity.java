package com.example.foundationcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

//自定义底部标签按钮（改造CheckBox）
public class TabButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_button);

        final TextView tv_select = findViewById(R.id.tv_select);
        CheckBox ck_tab = findViewById(R.id.ck_tab);

        // 给复选框设置勾选监听器
        ck_tab.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.getId() == R.id.ck_tab) {
                String desc = String.format("标签按钮被%s了", isChecked?"选中":"取消选中");
                tv_select.setText(desc);
            }
        });
    }
}