package com.example.foundationcontrols.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//允许设置线宽的分隔线
/*RecyclerView的样式属性*/
public class SpacesDecoration extends RecyclerView.ItemDecoration {
    private int space; // 空白间隔

    public SpacesDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = space; // 左边空白间隔
        outRect.right = space; // 右边空白间隔
        outRect.bottom = space; // 上方空白间隔
        outRect.top = space; // 下方空白间隔
    }
}
