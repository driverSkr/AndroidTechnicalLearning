package com.example.componentstest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.componentstest.R;
import com.example.componentstest.entity.Music;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicHolder> {

    private Context mContext;
    private List<Music> defaultMusicList;

    public MusicListAdapter(Context mContext, List<Music> defaultMusicList) {
        this.mContext = mContext;
        this.defaultMusicList = defaultMusicList;
    }

    public class MusicHolder extends RecyclerView.ViewHolder{

        public ImageView iv_music;
        public TextView tv_music;
        public MusicHolder(@NonNull View itemView) {
            super(itemView);
            iv_music = itemView.findViewById(R.id.iv_music);
            tv_music = itemView.findViewById(R.id.tv_music);
        }
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 根据布局文件item_linear.xml生成视图对象
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_delete_item,parent,false);
        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
        holder.iv_music.setImageResource(defaultMusicList.get(position).getMusicId());
        holder.tv_music.setText(defaultMusicList.get(position).getMusicName());

    }

    @Override
    public int getItemCount() {
        return defaultMusicList.size();
    }

    public void delete(){
        if (getItemCount() > 1){
            defaultMusicList.remove(1);
            notifyItemRemoved(1);
        } else if (getItemCount() == 1){
            defaultMusicList.remove(0);
            notifyItemRemoved(0);
        } else {
            Toast.makeText(mContext,"已无数据，无法删除",Toast.LENGTH_SHORT).show();
        }
    }

    public void add(){
        if (getItemCount() < 3){
            defaultMusicList.add(Music.getRandomMusic());
            notifyItemInserted(getItemCount() + 1);
        } else if (getItemCount() >= 3 && getItemCount() <= 20){
            defaultMusicList.add(2,Music.getRandomMusic());
            notifyItemInserted(2);
        } else {
            Toast.makeText(mContext,"已满20行，不能再增加数据",Toast.LENGTH_SHORT).show();
        }
    }
}
