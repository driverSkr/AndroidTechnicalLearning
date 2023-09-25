package com.example.foundationcontrols.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foundationcontrols.R;
import com.example.foundationcontrols.entity.Fruit;

import java.util.List;

/*
* RecyclerView适配器（带点击事件）的写法
* */
//RecyclerView并没有提供类似于setOnItemClickListener()这样的注册监听器方法，而是需要我们自己给子项具体的View去注册点击事件。这相比于ListView 来说，实现起来要复杂一些。
/*ListView 在点击事件上的处理并不人性化，setOnItemClickListener()方法注册的是子项的点击事件，但如果我想点击的是子项里具体的某一个按钮呢？虽然ListView 也能做到，但是实现起来就相对比较麻烦了。
为此，RecyclerView干脆直接摒弃了子项点击事件的监听器，让所有的点击事件都由具体的View去注册，就再没有这个困扰了。*/
public class FruitRecyclerClickAdapter extends RecyclerView.Adapter<FruitRecyclerClickAdapter.ViewHolder> {

    private final List<Fruit> fruitList;

    //有一个主构造函数，它用于把要展示的数据源传进来，我们后续的操作都将在这个数据源的基础上进行
    public FruitRecyclerClickAdapter(List<Fruit> fruitList){
        this.fruitList = fruitList;
    }

    /*内部类*/
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fruitImage;
        TextView fruitName;
        //主构造函数中要传入一个View参数，这个参数通常就是RecyclerView子项的最外层布局
        public ViewHolder(View itemView){
            super(itemView);
            //那么我们就可以通过findViewById()方法来获取布局中ImageView 和TextView的实例了
            fruitImage = itemView.findViewById(R.id.fruitImage);
            fruitName = itemView.findViewById(R.id.fruitName);
        }
    }

    /*用于创建ViewHolder实例的*/
    @NonNull
    @Override//我们在这个方法中将fruit_item_recycler布局加载进来，然后创建一个ViewHolder实例，并把加载出来的布局传入构造函数当中，最后将ViewHolder的实例返回
    public FruitRecyclerClickAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item_recycler, parent, false);
        /*注册点击事件*/
        ViewHolder viewHolder = new ViewHolder(view);

        //给最外层布局注册点击事件，itemView 表示的就是最外层布局
        viewHolder.itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Fruit fruit = fruitList.get(position);
            Toast.makeText(parent.getContext(),"you clicked view "+ fruit.getName(),Toast.LENGTH_SHORT).show();
        });
        //给ImageView注册点击事件
        viewHolder.fruitImage.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Fruit fruit = fruitList.get(position);
            Toast.makeText(parent.getContext(),"you clicked image "+ fruit.getName(),Toast.LENGTH_SHORT).show();
        });
        return viewHolder;
    }

    /*用于对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行*/
    //这里我们通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView当中即可
    @Override
    public void onBindViewHolder(@NonNull FruitRecyclerClickAdapter.ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());

        // 检查当前的 ViewHolder 是否为要隐藏的位置
        if (position == 2) {
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)holder.itemView.getLayoutParams();
            holder.itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
    }

    /*用于告诉RecyclerView一共有多少子项*/
    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    //根据位置获取当前view的类型
    /*@Override
    public int getItemViewType(int position) {
        //想要隐藏的位置（从0开始）
        int positionToHide = 2;
        if (position == positionToHide) {
            return 100;
        }
        return 1;
    }*/
}
