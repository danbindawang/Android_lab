package cn.edu.sysu.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 2017/11/1.
 */

public class MyBootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("com.example.broadcasttest.MY_BROADCAST")){
            Bundle bundle = intent.getExtras();
            int broad_id = bundle.getInt("broad_id");
            String broad_name =  bundle.getString("broad_name");
            String broad_price = bundle.getString("broad_price");
            String broad_message = bundle.getString("broad_message");
            int broad_image = bundle.getInt("broad_image");
            String broad_nameid = bundle.getString("broad_nameid");


            Intent sendtoMessage = new Intent(context, Message.class);
            sendtoMessage.putExtra("good_id",broad_id);
            sendtoMessage.putExtra("good_name",broad_name);
            sendtoMessage.putExtra("good_price",broad_price);
            sendtoMessage.putExtra("good_message",broad_message);
            sendtoMessage.putExtra("good_image",broad_image);
            sendtoMessage.putExtra("good_nameid",broad_nameid);
            Log.d("Debug", "onReceive: "+String.valueOf(broad_id));
            Log.d("Debug", "onReceive: "+String.valueOf(broad_name));

            /*
            *   传输商品详情信息
            */

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,sendtoMessage,0);


            //设置大图标
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),broad_image);

            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("新商品热卖")
                    .setContentText(broad_name + "仅售" + broad_price)
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bm)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification;
            notification = builder.build();
            manager.notify(0,notification);
        }
    }
}
