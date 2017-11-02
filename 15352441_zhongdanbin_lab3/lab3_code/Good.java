package cn.edu.sysu.lab3;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.*;
import java.util.List;

/**
 * Created by user on 2017/10/26.
 */

public  class Good {
    private String name;	//商品名称
    private String nameId;	//商品首字母大写
    private String price;	//商品价格
    private String message;	//商品补充信息
    private int image;		//商品图片
    private int id;			//商品编号
	
	//实例化商品
    public Good(int id, String name, String nameId, String price, String message, int image){
        this.id = id;
        this.name = name;
        this.nameId = nameId;
        this.price = price;
        this.message = message;
        this.image = image;
    }
	
    public int getId() {return id;}
    public String getName(){return name;}
    public String getnameId(){return nameId;}
    public String getPrice(){ return price;}
    public String getMessage() {return message;}
    public int getImage() {return image;}
}


