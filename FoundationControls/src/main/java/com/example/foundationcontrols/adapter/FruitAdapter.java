package com.example.foundationcontrols.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foundationcontrols.FruitDetailsActivity;
import com.example.foundationcontrols.R;
import com.example.foundationcontrols.entity.Fruit;

import java.util.List;

/*
* RecyclerView适配器
* */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            fruitImage = view.findViewById(R.id.fruitImage);
            fruitName = view.findViewById(R.id.fruitName);
        }
    }

    public FruitAdapter(List<Fruit> fruitList){
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent(mContext, FruitDetailsActivity.class);
                intent.putExtra(FruitDetailsActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitDetailsActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        //使用了Glide 来加载水果图片
        /*首先调用Glide.with()方法并传入一个Context、Activity或Fragment参数，然后调用load()方法加载图片，可以是一个URL地址，
        也可以是一个本地路径，或者是一个资源id，最后调用into()方法将图片设置到具体某一个ImageView 中就可以了*/
        /*为什么要使用Glide 而不是传统的设置图片方式呢？因为这次我从网上找的这些水果图片像素非常高，如果不进行压缩就直接展示的话，很容易引起内存溢出。而使用Glide 就完全不
        需要担心这回事，Glide 在内部做了许多非常复杂的逻辑操作，其中就包括了图片压缩，我们只需要安心按照Glide 的标准用法去加载图片就可以了*/
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
