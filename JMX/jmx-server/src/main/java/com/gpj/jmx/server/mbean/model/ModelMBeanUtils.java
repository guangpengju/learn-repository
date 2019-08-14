package com.gpj.jmx.server.mbean.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.management.Descriptor;
import javax.management.MBeanOperationInfo;
import javax.management.modelmbean.*;

/**
 * @className ModelMBeanUtils
 * @description TODO
 * @author GPJ
 * @date 2019/8/13 18:17
 * @version 1.0
 **/
@Slf4j
public class ModelMBeanUtils {
    private static final String OPERATION_ROLE_OPERATION = "operation";
    private static final String OPERATION_ROLE_GETTER = "getter";
    private static final String OPERATION_ROLE_SETTER = "setter";


    public static RequiredModelMBean createModlerMBean(Class clazz) {
        RequiredModelMBean model = null;
        try {
            model = new RequiredModelMBean();
            model.setManagedResource(clazz.newInstance(), "ObjectReference");
            ModelMBeanInfo info = createModelMBeanInfo(clazz);
            model.setModelMBeanInfo(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    private static ModelMBeanInfo createModelMBeanInfo(Class clazz) {
        // 设置构造函数
        ModelMBeanConstructorInfo[] constructorInfos = new ModelMBeanConstructorInfo[]{
            new ModelMBeanConstructorInfo("noArgs constructor", clazz.getConstructors()[0])
        };

        // 设置属性
        ModelMBeanAttributeInfo[] attrInfos = new ModelMBeanAttributeInfo[]{
                new ModelMBeanAttributeInfo("infoReadableAttr", "java.lang.String", "readable attribute", true, false, false, createFieldDescriptorSupport("infoReadableAttr")),
                new ModelMBeanAttributeInfo("infoWriteableAttr", "java.lang.String", "writeable attribute", true, true, false, createFieldDescriptorSupport("infoWriteableAttr")),
                new ModelMBeanAttributeInfo("time", "long", "sytem time", true, false, false, createFieldDescriptorSupport("time"))
        };

        // 设置方法
        ModelMBeanOperationInfo[] operaInfos = new ModelMBeanOperationInfo[]{
            new ModelMBeanOperationInfo("showInfo","show info attribute",
                    null,"java.lang.String", MBeanOperationInfo.INFO,
                    createMethodDescriptorSupport("showInfo",clazz.getName(), OPERATION_ROLE_OPERATION)),

            new ModelMBeanOperationInfo("getInfoReadableAttr","get attribute value of infoReadableAttr",
                    null,"java.lang.String", MBeanOperationInfo.INFO,
                    createMethodDescriptorSupport("getInfoReadableAttr",clazz.getName(), OPERATION_ROLE_GETTER)),

            new ModelMBeanOperationInfo("getInfoWriteableAttr","get attribute value of infoWriteableAttr",
                    null,"java.lang.String", MBeanOperationInfo.INFO,
                    createMethodDescriptorSupport("getInfoWriteableAttr",clazz.getName(), OPERATION_ROLE_GETTER)),

            new ModelMBeanOperationInfo("getTime","get attribute value of time",
                    null,"java.lang.String", MBeanOperationInfo.INFO,
                    createMethodDescriptorSupport("getTime",clazz.getName(), OPERATION_ROLE_GETTER)),

            new ModelMBeanOperationInfo("setInfoWriteableAttr","set attribute value of infoWriteableAttr",
                    null,"long", MBeanOperationInfo.INFO,
                    createMethodDescriptorSupport("setInfoWriteableAttr",clazz.getName(), OPERATION_ROLE_SETTER))
        };
        return new ModelMBeanInfoSupport(RequiredModelMBean.class.getName(), "info class requireModelMbean", attrInfos, null, operaInfos, null, null);
    }

    private static Descriptor createFieldDescriptorSupport(String attr){
        if(StringUtils.isNotBlank(attr)){
            Descriptor descriptor = new DescriptorSupport();
            descriptor.setField("name", attr);
            descriptor.setField("descriptorType", "attribute");
            descriptor.setField("displayName", attr);
            descriptor.setField("getMethod", "get" + toUpperFristChar(attr));
            descriptor.setField("setMethod", "set" + toUpperFristChar(attr));
            return descriptor;
        }
        return null;
    }
    private static Descriptor createMethodDescriptorSupport(String operation, String className, String role){
        if(StringUtils.isNotBlank(operation) && StringUtils.isNotBlank(className) && StringUtils.isNotBlank(role)){
            Descriptor descriptor = new DescriptorSupport();
            descriptor.setField("name", operation);
            descriptor.setField("descriptorType", "operation");
            descriptor.setField("class", className);
            descriptor.setField("role", role);
            return descriptor;
        }
        return null;
    }

    private static String toUpperFristChar(String name) {
        char[] charArray = name.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }

    private static String toLowerFristChar(String name) {
        char[] charArray = name.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
    }
}
