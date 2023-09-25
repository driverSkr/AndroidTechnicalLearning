package com.example.componentstest.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


public class FormatImageView extends AppCompatImageView {

    private Rect sourceRect;
    private Rect destRect;


    public FormatImageView(@NonNull Context context) {
        super(context);
        init();
    }

    public FormatImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 定义源矩形，用于从原始图片中裁剪
        sourceRect = new Rect();

        // 定义目标矩形，用于绘制在 View 中
        destRect = new Rect(0, 0, 140, 80);
    }

    @Override
    public void setImageResource(int resId) {
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), resId);

        // 裁剪图片多余的上下部分
        int cropHeight = originalBitmap.getHeight() - 80; // 多余的上下部分的高度
        sourceRect.set(0, cropHeight / 2, originalBitmap.getWidth(), originalBitmap.getHeight() - cropHeight / 2);

        // 创建一个新的裁剪后的 Bitmap
        Bitmap croppedBitmap = Bitmap.createBitmap(originalBitmap, sourceRect.left, sourceRect.top, sourceRect.width(), sourceRect.height());

        // 调用父类的方法设置新的 Bitmap
        super.setImageBitmap(croppedBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            Bitmap bitmap = getBitmapFromDrawable(getDrawable());

            if (bitmap != null) {
                canvas.drawBitmap(bitmap, sourceRect, destRect, null);
            }
        }
    }

    @Nullable
    private Bitmap getBitmapFromDrawable(android.graphics.drawable.Drawable drawable) {
        if (drawable instanceof android.graphics.drawable.BitmapDrawable) {
            return ((android.graphics.drawable.BitmapDrawable) drawable).getBitmap();
        }

        return null;
    }
}
