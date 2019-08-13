package com.gpj.jmx.server.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * @className Diners
 * @description TODO
 * @author GPJ
 * @date 2019/8/13 15:30
 * @version 1.0
 **/
@Slf4j
public class Diners implements DinersMBean {
    public boolean wait;

    @Override
    public boolean isWait() {
        return wait;
    }

    public void foodNumChange(String num){
        int foodNum = Integer.parseInt(num);
        if(foodNum > 0){
            log.info("食物充足开吃!");
            wait = false;
        }else{
            log.info("没吃的啦!乖乖等待...");
            wait = true;
        }
    }
}
