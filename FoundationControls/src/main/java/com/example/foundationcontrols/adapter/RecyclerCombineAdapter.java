package com.example.foundationcontrols.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foundationcontrols.R;
import com.example.foundationcontrols.entity.NewsInfo;
import com.example.foundationcontrols.util.Utils;
import com.example.foundationcontrols.widget.RecyclerExtras;
import com.example.foundationcontrols.widget.RecyclerExtras.OnItemClickListener;
import com.example.foundationcontrols.widget.RecyclerExtras.OnItemLongClickListener;

import java.util.List;

public class RecyclerCombineAdapter extends RecyclerView.Adapter<RecyclerCombineAdapter.ItemHolder> implements
        OnItemClickListener,OnItemLongClickListener {

    private Context mContext; // 声明一个上下文对象
    private List<NewsInfo> mGoodsList;

    public RecyclerCombineAdapter(Context context, List<NewsInfo> goodsList) {
        mContext = context;
        mGoodsList = goodsList;
    }

    // 创建列表项的视图持有者
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 根据布局文件item_combine.xml生成视图对象
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_combine, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.iv_pic.setImageResource(mGoodsList.get(position).pic_id);
        holder.tv_title.setText(mGoodsList.get(position).title);
        ViewGroup.LayoutParams iv_param = holder.iv_pic.getLayoutParams();
        if (position==0 || position==1) {
            iv_param.height = Utils.dip2px(mContext, 130);
        } else {
            iv_param.height = Utils.dip2px(mContext, 60);
        }
        holder.iv_pic.setLayoutParams(iv_param);
        // 列表项的点击事件需要自己实现
        holder.ll_item.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
        // 列表项的长按事件需要自己实现
        holder.ll_item.setOnLongClickListener(v -> {
            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener.onItemLongClick(v, position);
            }
            return true;
        });
    }

    // 获取列表项的个数
    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }

    // 定义列表项的视图持有者
    public class ItemHolder extends RecyclerView.ViewHolder {
        public LinearLayout ll_item; // 声明列表项的线性布局
        public ImageView iv_pic; // 声明列表项图标的图像视图
        public TextView tv_title; // 声明列表项标题的文本视图

        public ItemHolder(View v) {
            super(v);
            ll_item = v.findViewById(R.id.ll_item);
            iv_pic = v.findViewById(R.id.iv_pic);
            tv_title = v.findViewById(R.id.tv_title);
        }
    }

    // 声明列表项的点击监听器对象
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 声明列表项的长按监听器对象
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    // 处理列表项的点击事件
    public void onItemClick(View view, int position) {
        String desc = String.format("您点击了第%d项，推荐频道是%s", position + 1,
                mGoodsList.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }

    // 处理列表项的长按事件
    public void onItemLongClick(View view, int position) {
        String desc = String.format("您长按了第%d项，推荐频道是%s", position + 1,
                mGoodsList.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }
}
