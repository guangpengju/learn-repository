package com.gpj.spring.bean;

public class LearnBeanImpl implements LearnBean {
    private String beanName;
    private String version;

    public LearnBeanImpl() {
    	this("testbean","0.0.1");
    }

    public LearnBeanImpl(String beanName, String version) {
        this.beanName = beanName;
        this.version = version;
    }

    public String info() {
        return "bean信息:【name:" + beanName + ";version:" + version + "】";
    }

    
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
