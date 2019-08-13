package com.gpj.jmx.server.listener;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * @className Cook
 * @description TODO
 * @author GPJ
 * @date 2019/8/13 15:30
 * @version 1.0
 **/
public class Cook extends NotificationBroadcasterSupport implements CookMBean{
    public int foodNum;

    @Override
    public int getFoodNum() {
        return foodNum;
    }

    @Override
    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
        foodNumChange();
    }

    private void foodNumChange(){
        //通知名称；谁发起的通知；序列号；发起通知时间；发送的消息
        Notification notify = new Notification("jmxBean:cook:foodNumchange",this, 1,System.currentTimeMillis(),foodNum + "");
        sendNotification(notify);
    }
}
