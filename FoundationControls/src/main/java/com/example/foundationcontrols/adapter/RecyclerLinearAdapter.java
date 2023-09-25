package com.example.foundationcontrols.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foundationcontrols.R;
import com.example.foundationcontrols.entity.NewsInfo;

import java.util.List;

//线性的Recycler适配器：公众号消息列表
/*AndroidStudio开发实战代码*/
public class RecyclerLinearAdapter extends RecyclerView.Adapter<RecyclerLinearAdapter.ItemHolder> {

    private final static String TAG = "RecyclerLinearAdapter";
    private Context mContext; // 声明一个上下文对象
    private List<NewsInfo> mPublicList; // 公众号列表

    public RecyclerLinearAdapter(Context mContext, List<NewsInfo> mPublicList) {
        this.mContext = mContext;
        this.mPublicList = mPublicList;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView iv_pic; // 声明列表项图标的图像视图
        public TextView tv_title; // 声明列表项标题的文本视图
        public TextView tv_desc; // 声明列表项描述的文本视图

        public ItemHolder(View v) {
            super(v);
            iv_pic = v.findViewById(R.id.iv_pic);
            tv_title = v.findViewById(R.id.tv_title);
            tv_desc = v.findViewById(R.id.tv_desc);
        }
    }

    // 创建列表项的视图持有者
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 根据布局文件item_linear.xml生成视图对象
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_linear,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.iv_pic.setImageResource(mPublicList.get(position).pic_id);
        holder.tv_title.setText(mPublicList.get(position).title);
        holder.tv_desc.setText(mPublicList.get(position).desc);
    }

    // 获取列表项的个数
    @Override
    public int getItemCount() {
        return mPublicList.size();
    }

    // 获取列表项的类型，这里的类型与onCreateViewHolder方法的viewType参数保持一致
//    public int getItemViewType(int position) {
//        return 0;
//    }
//
//    // 获取列表项的编号
//    public long getItemId(int position) {
//        return position;
//    }
}
