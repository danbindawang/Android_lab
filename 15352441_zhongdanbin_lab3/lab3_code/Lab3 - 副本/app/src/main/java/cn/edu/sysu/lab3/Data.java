package cn.edu.sysu.lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/10/27.
 */

public class Data {
    private static List<Good> shoplist = new ArrayList<>();
    private static boolean Tag[] = {false,false,false,false,false,false,false,false,false,false};
    public static Good_listAdapter listadapter;
    public static List<Good> getShoplist(){
        return shoplist;
    }
    public static void setData(Good good){
        Data.shoplist.add(good);
    }
    public static void removeData(int position){
        Data.shoplist.remove(position);
    }
    public static boolean getTag(int i) {return Tag[i];}
    public static void setTag(int i) { Tag[i] = !Tag[i];}
}
