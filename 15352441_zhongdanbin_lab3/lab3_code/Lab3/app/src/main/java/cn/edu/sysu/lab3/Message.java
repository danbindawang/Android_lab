package cn.edu.sysu.lab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Message extends AppCompatActivity {

    boolean tag = false;


    ImageView ShopCar;
    ImageView Back;
    ImageView Star;
    ///////////////////////////

    /////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        final int data_id = (int)getIntent().getSerializableExtra("good_id");

        final String data_name = (String) getIntent().getSerializableExtra("good_name");
        TextView name = (TextView)findViewById(R.id.good_name);
        name.setText(data_name);

        final String data_price = (String) getIntent().getSerializableExtra("good_price");
        TextView price = (TextView) findViewById(R.id.price);
        price.setText(data_price);

        final String data_message = (String) getIntent().getSerializableExtra("good_message");
        TextView message = (TextView) findViewById(R.id.weight);
        message.setText(data_message);

        final int data_image = (int) getIntent().getSerializableExtra("good_image");
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(data_image);

        final String data_nameid = (String) getIntent().getSerializableExtra("good_nameid");



        ShopCar =  (ImageView) findViewById(R.id.shopcar);
        ShopCar.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v){
                Good new_shop = new Good(data_id,data_name,data_nameid,data_price,data_message,data_image);
                Data.setData(new_shop);
                Toast.makeText(Message.this,"商品已添加到购物车",Toast.LENGTH_SHORT)
                        .show();
                 Data.listadapter.notifyDataSetChanged();
            }
        });

        Back = (ImageView) findViewById(R.id.back);
        Back.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v){
                returnData();
            }
            private void returnData(){
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });


        Star = (ImageView) findViewById(R.id.collect);
        Star.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v){
                Data.setTag(data_id);
                if(Data.getTag(data_id) == false){
                    Star.setImageResource(R.mipmap.empty_star);
                }
                else{
                    Star.setImageResource(R.mipmap.full_star);
                }
            }
        });

        if(Data.getTag(data_id) == false){
            Star.setImageResource(R.mipmap.empty_star);
        }
        else{
            Star.setImageResource(R.mipmap.full_star);
        }

    }




}
