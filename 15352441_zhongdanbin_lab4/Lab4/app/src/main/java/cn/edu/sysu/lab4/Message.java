package cn.edu.sysu.lab4;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class Message extends AppCompatActivity {

    boolean tag = false;


    ImageView ShopCar;
    ImageView Back;
    ImageView Star;
    DynamicReceiver dynamicReceiver;
    static final String DYNAMICATION = "com.example.lab4.DynamicReceiver";
    int notifyid = 1;

    int data_id;
    String data_name;
    String data_price;
    String data_message;
    int data_image;
    String data_nameid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        data_id = getIntent().getIntExtra("good_id",0);
        Log.d("Debug", "onCreate: "+getIntent().getExtras().getInt("good_id"));

        data_name = (String) getIntent().getSerializableExtra("good_name");
        Log.d("Debug", "onCreate: "+getIntent().getExtras().getInt("good_name"));
        TextView name = (TextView)findViewById(R.id.good_name);
        name.setText(data_name);

        data_price = (String) getIntent().getSerializableExtra("good_price");
        TextView price = (TextView) findViewById(R.id.price);
        price.setText(data_price);

        data_message = (String) getIntent().getSerializableExtra("good_message");
        TextView message = (TextView) findViewById(R.id.weight);
        message.setText(data_message);

        data_image = (int) getIntent().getSerializableExtra("good_image");
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(data_image);

        data_nameid = (String) getIntent().getSerializableExtra("good_nameid");


        //注册动态广播
        IntentFilter dynamic_filter = new IntentFilter();
        dynamic_filter.addAction(DYNAMICATION);
        dynamicReceiver = new DynamicReceiver();
        registerReceiver(dynamicReceiver,dynamic_filter);


        ShopCar =  (ImageView) findViewById(R.id.shopcar);
        ShopCar.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("com.example.lab4.DynamicReceiver");
                intent.putExtra("dy_image",data_image);
                intent.putExtra("dy_name",data_name);
                sendBroadcast(intent);
                Good new_shop = new Good(data_id,data_name,data_nameid,data_price,data_message,data_image);
                Data.setData(new_shop);
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



    @Override
    protected  void onDestroy(){
        super.onDestroy();
        unregisterReceiver(dynamicReceiver);

    }

    class DynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive (Context context, Intent intent){
            if(intent.getAction().equals(DYNAMICATION)){
                Bundle bundle = intent.getExtras();
                int dy_image = bundle.getInt("dy_image");
                String dy_name = bundle.getString("dy_name");
                Bitmap bm = BitmapFactory.decodeResource(context.getResources(),dy_image);

               Intent sendtoMessage = new Intent(context,Lab4.class);
               PendingIntent pendingIntent = PendingIntent.getActivity(context,notifyid,sendtoMessage,PendingIntent.FLAG_UPDATE_CURRENT);

                EventBus.getDefault().post(new MessageEvent(true));

                Notification.Builder builder = new Notification.Builder(context);
                builder.setContentTitle("马上下单")
                        .setContentText(dy_name+"已添加到购物车")
                        .setTicker("您有一条新消息")
                        .setLargeIcon(bm)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification;
                notification = builder.build();
                manager.notify(notifyid,notification);

           }
        }
    }
}
