package com.gpj.jmx.server.mbean.standard;

/**
 * @className InfoMBean
 * @description TODO
 * @author GPJ
 * @date 2019/7/25 18:26
 * @version 1.0
 **/
public interface InfoMBean {
    public String getInfoName();

    public void setInfoName(String infoName);

    public String getInfoType();

    public void setInfoType(String infoType);

    public String getInfoVal();

    public void setInfoVal(String infoVal);

    public long getTime();

    public void setTime(long time);

    public String showInfo();

}
