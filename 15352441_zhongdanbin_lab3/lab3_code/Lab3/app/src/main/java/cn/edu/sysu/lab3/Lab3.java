package cn.edu.sysu.lab3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lab3 extends Activity {

    boolean  tag = false;
    private List<Good> goodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);

        initFruits();


        ////////////////////////////////////////////////
        Random random = new Random();
        int n = random.nextInt(10);

        //静态注册
        Intent intentBroadcast = new Intent("com.example.broadcasttest.MY_BROADCAST");
        Bundle bundle = new Bundle();
        bundle.putInt("broad_id",goodList.get(n).getId());
        bundle.putString("broad_name",goodList.get(n).getName());
        bundle.putString("broad_price",goodList.get(n).getPrice());
        bundle.putString("broad_message",goodList.get(n).getMessage());
        bundle.putInt("broad_image",goodList.get(n).getImage());
        bundle.putString("broad_nameid",goodList.get(n).getnameId());
        intentBroadcast.putExtras(bundle);
        sendBroadcast(intentBroadcast);
        ///////////////////////////////////////////////////////

        Data.listadapter = new Good_listAdapter(this,R.layout.item,Data.getShoplist());
        Data.listadapter.notifyDataSetChanged();


        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Good_recyclerAdapter adapter = new Good_recyclerAdapter(goodList);
        recyclerView.setAdapter(adapter);

        Good title = new Good(0,"购物车","*","价格","",0);
        Data.setData(title);

        final ListView listView = (ListView) findViewById(R.id.list_view);
        Data.listadapter.notifyDataSetChanged();
        listView.setAdapter(Data.listadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                Good good = Data.getShoplist().get(position);
                Activity CurrentActivity = (Activity)view.getContext();
                Intent intent = new Intent("com.example.vincent.lab3.ACTION_START");

                intent.putExtra("good_id",Data.getShoplist().get(position).getId());
                intent.putExtra("good_name",Data.getShoplist().get(position).getName());
                intent.putExtra("good_price",Data.getShoplist().get(position).getPrice());
                intent.putExtra("good_message",Data.getShoplist().get(position).getMessage());
                intent.putExtra("good_image",Data.getShoplist().get(position).getImage());
                intent.putExtra("good_nameid",Data.getShoplist().get(position).getnameId());
                CurrentActivity.startActivityForResult(intent,1);
            }

        });



        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String[] items = new String[]{"从购物车中移除"+Data.getShoplist().get(position).getName()+"?"};
                mbuilder.setTitle("移除商品")
                        .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i){}
                        })
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i){
                                Data.removeData(position);
                                Data.listadapter.notifyDataSetChanged();
                            }
                        })
                        .setItems(items,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i){}
                        })
                        .show();

                return true;
            }

        });


        final FloatingActionButton FloatButton = (FloatingActionButton)findViewById(R.id.fab);

        FloatButton.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v){
                tag = !tag;
                if(tag == false){
                    FloatButton.setImageResource(R.mipmap.shoplist);
                    listView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                }
                else{
                    FloatButton.setImageResource(R.mipmap.mainpage);
                    listView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initFruits(){
        Good arla = new Good(1,"Arla","A","¥ 59.00","产地    德国",R.drawable.arla);
        goodList.add(arla);
        Good borggreve = new Good(2,"Borggreve","B","¥ 28.90","重量    640g",R.drawable.borggreve);
        goodList.add(borggreve);
        Good devondale = new Good(3,"Devondale","D","¥ 79.00","产地    澳大利亚",R.drawable.devondale);
        goodList.add(devondale);
        Good enchatedforest = new Good(4,"EnchatedForest","E","¥ 5.00","作者    Johanna Basford",R.drawable.enchatedforest);
        goodList.add(enchatedforest);
        Good ferrero = new Good(5,"Ferrero","F","¥ 132.59","重量    300g",R.drawable.ferrero);
        goodList.add(ferrero);
        Good kindle = new Good(6,"Kindle","K","¥ 2399.00","版本    8GB",R.drawable.kindle);
        goodList.add(kindle);
        Good lindt = new Good(7,"Lindt","L","¥ 139.43","重量    249g",R.drawable.lindt);
        goodList.add(lindt);
        Good maltersers = new Good(8,"Maltesers","M","¥ 141.43","重量    118g",R.drawable.maltesers);
        goodList.add(maltersers);
        Good mcvitie = new Good(9,"Mcvitie","M","¥ 14.90","产地    英国",R.drawable.mcvitie);
        goodList.add(mcvitie);
        Good waitrose = new Good(10,"Waitrose","W","¥ 179.00","重量    2Kg",R.drawable.waitrose);
        goodList.add(waitrose);
    }

}

