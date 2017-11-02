package cn.edu.sysu.lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/10/27.
 */

public class Data {
	//购物车内商品信息
    private static List<Good> shoplist = new ArrayList<>();
    public static Good_listAdapter listadapter;
	//商品对应收藏状态
    private static boolean Tag[] = {false,false,false,false,false,false,false,false,false,false};
	//返回购物车列表
    public static List<Good> getShoplist(){
        return shoplist;
    }
	//向购物车内添加商品
    public static void setData(Good good){
        Data.shoplist.add(good);
    }
	//从购物车移除商品
    public static void removeData(int position){
        Data.shoplist.remove(position);
    }
	//返回商品的收藏状态
    public static boolean getTag(int i) {return Tag[i];}
	//改变商品的收藏状态
    public static void setTag(int i) { Tag[i] = !Tag[i];}
}
