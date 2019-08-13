package com.gpj.jmx.server.listener;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * @className DinersLisenter
 * @description TODO
 * @author GPJ
 * @date 2019/8/13 15:48
 * @version 1.0
 **/
public class DinersLisenter implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        if(handback instanceof Diners)
        {
            Diners hello = (Diners)handback;
            hello.foodNumChange(notification.getMessage());
        }
    }
}
