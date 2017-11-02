package cn.edu.sysu.lab4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by user on 2017/10/26.
 */

public class Good_recyclerAdapter extends RecyclerView.Adapter<Good_recyclerAdapter.ViewHolder>{
    private List<Good> mGoodList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        TextView fruitId;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            fruitView = view;
            fruitId = (TextView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    public Good_recyclerAdapter(List<Good> goodList){
        mGoodList = goodList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Activity CurrentActivity = (Activity)v.getContext();
                Intent intent = new Intent("com.example.vincent.lab4.ACTION_START");

                int position = holder.getAdapterPosition();
                intent.putExtra("good_id", mGoodList.get(position).getId());
                intent.putExtra("good_name", mGoodList.get(position).getName());
                intent.putExtra("good_price", mGoodList.get(position).getPrice());
                intent.putExtra("good_message", mGoodList.get(position).getMessage());
                intent.putExtra("good_image", mGoodList.get(position).getImage());
                intent.putExtra("good_nameid", mGoodList.get(position).getnameId());
                CurrentActivity.startActivityForResult(intent,1);

            }
        });

        holder.fruitView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                int position = holder.getAdapterPosition();
                Activity CurrentActivity = (Activity)v.getContext();
                Toast.makeText(CurrentActivity,
                        "商品"+ mGoodList.get(position).getName()+"已被移除",Toast.LENGTH_SHORT)
                        .show();

                mGoodList.remove(position);
                notifyDataSetChanged();
                return false;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Good good = mGoodList.get(position);
        holder.fruitId.setText(good.getnameId());
        holder.fruitName.setText(good.getName());
    }

    @Override
    public int getItemCount(){
        return mGoodList.size();
    }

}
