package com.example.foundationcontrols.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foundationcontrols.R;
import com.example.foundationcontrols.entity.Fruit;

import java.util.List;

/*
* RecyclerView 适配器标准的写法
* */
public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.ViewHolder> {

    private List<Fruit> fruitList;

    //有一个主构造函数，它用于把要展示的数据源传进来，我们后续的操作都将在这个数据源的基础上进行
    public FruitRecyclerAdapter(List<Fruit> fruitList){
        this.fruitList = fruitList;
    }

    /*内部类*/
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fruitImage;
        TextView fruitName;
        //主构造函数中要传入一个View参数，这个参数通常就是RecyclerView子项的最外层布局
        public ViewHolder(View view){
            super(view);
            //那么我们就可以通过findViewById()方法来获取布局中ImageView 和TextView的实例了
            fruitImage = view.findViewById(R.id.fruitImage);
            fruitName = view.findViewById(R.id.fruitName);
        }
    }

    /*用于创建ViewHolder实例的*/
    @NonNull
    @Override//我们在这个方法中将fruit_item_recycler布局加载进来，然后创建一个ViewHolder实例，并把加载出来的布局传入构造函数当中，最后将ViewHolder的实例返回
    public FruitRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item_recycler_staggered_grid, parent, false);

        return new ViewHolder(view);
    }

    /*用于对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行*/
    //这里我们通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView当中即可
    @Override
    public void onBindViewHolder(@NonNull FruitRecyclerAdapter.ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    /*用于告诉RecyclerView一共有多少子项*/
    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
