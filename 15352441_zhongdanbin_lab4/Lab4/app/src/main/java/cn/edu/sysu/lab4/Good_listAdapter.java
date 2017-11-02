package cn.edu.sysu.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.*;
import java.util.List;

/**
 * Created by user on 2017/10/26.
 */

public class Good_listAdapter extends ArrayAdapter<Good> {
    private int resourceId;

    public Good_listAdapter(Context context, int textViewResourceId,
                            List<Good> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Good good = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView fruitImage = (TextView)view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView)view.findViewById(R.id.fruit_name);
        TextView fruitPrice = (TextView) view.findViewById(R.id.fruit_price);
        fruitImage.setText(good.getnameId());
        fruitName.setText(good.getName());
        fruitPrice.setText(good.getPrice());
        return view;
    }
}
