package com.example.personalDefinitionView.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerTabStrip;

import com.example.personalDefinitionView.R;
import com.example.personalDefinitionView.util.Utils;

//给翻页标签栏添加新属性textColor、textSize
/*改造PagerTabStrip，使得CustomPagerTab在XML里可以设置textColor和textSize*/
public class CustomPagerTab extends PagerTabStrip {
    private final static String TAG = "CustomPagerTab";
    private int textColor = Color.BLACK;    //文本颜色
    private int textSize = 15;  //文本大小

    public CustomPagerTab(@NonNull Context context) {
        super(context);
    }

    public CustomPagerTab(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null){
            // 根据CustomPagerTab的属性定义，从XML文件中获取属性数组描述
            TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.CustomPagerTab);
            // 根据属性描述定义，获取XML文件中的文本颜色（第二个参数为默认值）
            textColor = attrArray.getColor(R.styleable.CustomPagerTab_textColor,textColor);
            // 根据属性描述定义，获取XML文件中的文本大小
            // getDimension得到的是px值，需要转换为sp值
            textSize = Utils.px2sp(context, attrArray.getDimension(
                    R.styleable.CustomPagerTab_textSize, textSize));
            Log.d(TAG, "origin textSize="+attrArray.getDimension(
                    R.styleable.CustomPagerTab_textSize, textSize));
            Log.d(TAG, "textColor=" + textColor + ", textSize=" + textSize);
            attrArray.recycle(); // 回收属性数组描述
        }
    }

    //    //PagerTabStrip没有三个参数的构造方法
//    public CustomPagerTab(Context context, AttributeSet attrs, int defStyleAttr) {
//    }


    @Override
    protected void onDraw(Canvas canvas) { // 绘制方法
        super.onDraw(canvas);
        setTextColor(textColor);    //设置标题文字的文本颜色
        setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);   //设置标题文字的文本大小
    }
}
