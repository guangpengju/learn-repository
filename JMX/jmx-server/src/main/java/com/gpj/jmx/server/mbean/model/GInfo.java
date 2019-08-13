package com.gpj.jmx.server.mbean.model;

import com.gpj.jmx.server.mbean.standard.InfoMBean;
import lombok.Data;
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
public class GInfo {
    private String infoReadableAttr = "readable attributes";
    private String infoWriteableAttr = "write attributes";
    private long time;

    public String getInfoReadableAttr() {
        return infoReadableAttr;
    }

    public void setInfoReadableAttr(String infoReadableAttr) {
        this.infoReadableAttr = infoReadableAttr;
    }

    public String getInfoWriteableAttr() {
        return infoWriteableAttr;
    }

    public void setInfoWriteableAttr(String infoWriteableAttr) {
        this.infoWriteableAttr = infoWriteableAttr;
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public String showInfo() {
        return this.toString();
    }
}
