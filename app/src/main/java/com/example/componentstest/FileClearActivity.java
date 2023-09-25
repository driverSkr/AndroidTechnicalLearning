package com.example.componentstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.componentstest.utils.FileClearUtil;
import com.example.componentstest.utils.FileUtil;
import com.example.componentstest.utils.FileUtils;

import java.io.File;

public class FileClearActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_clear);

        FileUtils utils = new FileUtils(this);

        findViewById(R.id.click).setOnClickListener(v -> {
            Toast.makeText(this, "你点击了这个按钮", Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.click1).setOnClickListener(v -> {
            deleteFolderFile(utils.getDcimPath()+"/aa");
            Log.d("boge","删除成功");
        });
    }

    public boolean deleteFolderFile(String filePath) {
        //用于操作和管理文件夹
        File file = new File(filePath);
        try {
            //用于操作和管理文件夹
            //File file = new File(filePath);
            //这里检查文件夹是否存在，避免在删除操作时出现错误
            if (file.exists()) {
                //获取指定文件夹中的所有文件和子文件夹，将它们存储在一个 File 数组中
                File[] files = file.listFiles();
                if (files != null) {
                    for (File childFile : files) {
                        //如果当前处理的是一个文件（不是文件夹），则执行这个条件块
                        if (childFile.isFile()) {
                            //使用 delete() 方法删除当前文件
                            childFile.delete();
                        } else {//如果当前处理的是一个文件夹，执行这个条件块
                            deleteFolderFile(childFile.getAbsolutePath());//获取文件或文件夹的绝对路径
                            childFile.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File[] files1 = file.listFiles();
        if (files1 == null || files1.length == 0){
            Log.d("boge","删除成功");
            return true;
        }
        Log.d("boge","删除失败");
        return false;
    }
}