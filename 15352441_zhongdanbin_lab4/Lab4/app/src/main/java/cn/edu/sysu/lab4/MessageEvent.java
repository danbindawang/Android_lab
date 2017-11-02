package cn.edu.sysu.lab4;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by user on 2017/11/1.
 */

public  class MessageEvent {
    public boolean tag;
    public MessageEvent(boolean tag){
        this.tag = tag;
    }
    void setTag(boolean tag){
        this.tag = tag;
    }
}
