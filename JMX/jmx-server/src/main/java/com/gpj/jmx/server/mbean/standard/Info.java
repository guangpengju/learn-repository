package com.gpj.jmx.server.mbean.standard;

import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className Info
 * @description TODO
 * @author GPJ
 * @date 2019/7/25 18:26
 * @version 1.0
 **/
@NoArgsConstructor
@ToString
public class Info implements InfoMBean{
    public String infoName;
    public String infoType;
    public String infoVal;
    public long time;

    @Override
    public String getInfoName() {
        return infoName;
    }

    @Override
    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    @Override
    public String getInfoType() {
        return infoType;
    }

    @Override
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    @Override
    public String getInfoVal() {
        return infoVal;
    }

    @Override
    public void setInfoVal(String infoVal) {
        this.infoVal = infoVal;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String showInfo() {
        return this.toString();
    }
}
