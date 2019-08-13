package com.gpj.jmx.server.mbean.dynamic;

import org.apache.commons.lang3.StringUtils;

import javax.management.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @className dynamic
 * @description TODO
 * @author GPJ
 * @date 2019/8/13 17:13
 * @version 1.0
 **/
public class DynamicInfo implements DynamicMBean {
    private String infoReadableAttr = "readable attributes";
    private String infoWriteableAttr = "write attributes";


    private MBeanInfo mBeanInfo = null;

    private String className;//class名
    private String description;//描述

    private MBeanConstructorInfo[] constructors;// 构造函数

    private MBeanAttributeInfo[] attributes;// 属性
    private MBeanOperationInfo[] operations;// 方法

    MBeanNotificationInfo[] mBeanNotificationInfoArray;// MBean间通知


    public DynamicInfo() {
        init();
    }

    public String showInfo() {
        return "infoReadableAttr:'" + infoReadableAttr + "';infoWriteableAttr:'" + infoWriteableAttr + "'";
    }

    private void init() {
        className = this.getClass().getName();
        description = "simple implementation of a dynamic MBean";

        // 设定构造函数
        constructors = new MBeanConstructorInfo[]{
            new MBeanConstructorInfo("noArgs constructor", this.getClass().getConstructors()[0])
        };

        // 设定属性
        attributes = new MBeanAttributeInfo[]{
            new MBeanAttributeInfo("infoReadableAttr", "java.lang.String", "readable attribute description.", true, false, false),
            new MBeanAttributeInfo("infoWriteableAttr", "java.lang.String", "writeable attribute description.", true, true, false)
        };

        // 设定方法
        operations = new MBeanOperationInfo[]{
            new MBeanOperationInfo("showInfo", "showInfo method.", null, "java.lang.String", MBeanOperationInfo.INFO)
        };

        mBeanNotificationInfoArray = new MBeanNotificationInfo[0];

        mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations, mBeanNotificationInfoArray);
    }


    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        try {
            Field field = this.getClass().getDeclaredField(attribute);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        try {
            if(attribute != null){
                String name = attribute.getName();
                if(StringUtils.isNotBlank(name)){
                    Field field = this.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(this,attribute.getValue());
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        AttributeList list = new AttributeList();
        if(attributes != null && attributes.length > 0){
            for (String attribute : attributes) {
                try {
                    Object value = getAttribute(attribute);
                    list.add(new Attribute(attribute, value));
                } catch (AttributeNotFoundException | MBeanException | ReflectionException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        AttributeList list = new AttributeList();
        if(attributes != null && !attributes.isEmpty()) {
            for (Object attribute : attributes) {
                try {
                    setAttribute((Attribute) attribute);
                    list.add(attribute);
                } catch (AttributeNotFoundException | InvalidAttributeValueException | MBeanException | ReflectionException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        try {
            Method method = this.getClass().getMethod(actionName, createParamsType(params));
            return method.invoke(this, params);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return this.mBeanInfo;
    }

    private Class[] createParamsType(Object[] params){
        if(params != null && params.length > 0){
            Class[] clazzes = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                clazzes[i] = params[i].getClass();
            }
            return clazzes;
        }
        return null;
    }
}
