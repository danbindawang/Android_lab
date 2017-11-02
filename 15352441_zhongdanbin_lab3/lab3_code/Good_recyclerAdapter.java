package cn.edu.sysu.lab3;

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
    //私有元素Good型List
	private List<Good> mGoodList;
	
	//继承自RecyclerView.ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        TextView fruitId;
        TextView fruitName;
		//构造ViewHolder，传入RecycleView子项的最外层布局
        public ViewHolder(View view){
            super(view);
            fruitView = view;
			//获取布局中的实例
            fruitId = (TextView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }
	
	//Good_recyclerAdapter构造函数，将数据源传入
    public Good_recyclerAdapter(List<Good> goodList){
        mGoodList = goodList;
    }
    @Override
	
	//修改ViewHolder，处理点击、长按事件
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
		
		//处理点击事件
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Activity CurrentActivity = (Activity)v.getContext();
				
				//利用Intent隐式跳转到商品详细信息界面
                Intent intent = new Intent("com.example.vincent.lab3.ACTION_START");
				//返回当前被点击的位置
                int position = holder.getAdapterPosition();
				//通过putExtra将被点击的对应商品信息传入商品详细信息界面
                intent.putExtra("good_id", mGoodList.get(position).getId());
                intent.putExtra("good_name", mGoodList.get(position).getName());
                intent.putExtra("good_price", mGoodList.get(position).getPrice());
                intent.putExtra("good_message", mGoodList.get(position).getMessage());
                intent.putExtra("good_image", mGoodList.get(position).getImage());
                intent.putExtra("good_nameid", mGoodList.get(position).getnameId());
                //界面跳转
				CurrentActivity.startActivityForResult(intent,1);
            }
        });

		//处理长按事件
        holder.fruitView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                int position = holder.getAdapterPosition();
                Activity CurrentActivity = (Activity)v.getContext();
                //弹出Toast信息提示商品被移除
				Toast.makeText(CurrentActivity,
                        "商品"+ mGoodList.get(position).getName()+"已被移除",Toast.LENGTH_SHORT)
                        .show();
				
				//将该商品移出mGoodList
                mGoodList.remove(position);
				//重新加载Good_recylerAdapter
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
