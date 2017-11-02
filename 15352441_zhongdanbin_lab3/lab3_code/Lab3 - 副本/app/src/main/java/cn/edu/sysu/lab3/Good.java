package cn.edu.sysu.lab3;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.*;
import java.util.List;

/**
 * Created by user on 2017/10/26.
 */

public  class Good {
    private String name;
    private String nameId;
    private String price;
    private String message;
    private int image;
    private int id;

    public Good(int id, String name, String nameId, String price, String message, int image){
        this.id = id;
        this.name = name;
        this.nameId = nameId;
        this.price = price;
        this.message = message;
        this.image = image;
    }
    public int getId() {return id;}
    public String getName(){
        return name;
    }
    public String getnameId(){
        return nameId;
    }
    public String getPrice(){ return price;}
    public String getMessage() {return message;}
    public int getImage() {return image;}

}


