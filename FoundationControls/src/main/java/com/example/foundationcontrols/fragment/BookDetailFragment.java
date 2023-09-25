package com.example.foundationcontrols.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foundationcontrols.R;

//书籍详细界面
/*GoodsPagerAdapter适配器调用*/
public class BookDetailFragment extends Fragment {
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        // 根据布局文件fragment_book_detail.xml生成视图对象
        mView = inflater.inflate(R.layout.fragment_book_detail,container,false);
        return mView;
    }
}
