package com.gpj.jmx.server.mbean.model;

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
public class ModelMBeanUtils {
    private static final boolean READABLE = true;
    private static final boolean WRITABLE = true;
    private static final boolean BOOLEAN = true;
    private static final String STRING_CLASS = "java.lang.String";


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
        // 构造函数
        ModelMBeanConstructorInfo[] constructorInfos = new ModelMBeanConstructorInfo[]{
            new ModelMBeanConstructorInfo("noArgs constructor", clazz.getConstructors()[0])
        };


        ModelMBeanAttributeInfo[] attrInfos = new ModelMBeanAttributeInfo[]{
                new ModelMBeanAttributeInfo("infoReadableAttr", "java.lang.String", "readable attribute", true, false, false, createDescriptorSupport("infoReadableAttr")),
                new ModelMBeanAttributeInfo("InfoWriteableAttr", "java.lang.String", "writeable attribute", true, true, false, null),
                new ModelMBeanAttributeInfo("Time", "long", "sytem time", true, false, false, null)
        };

        // 构造方法
        ModelMBeanOperationInfo[] operaInfos = new ModelMBeanOperationInfo[]{
            new ModelMBeanOperationInfo("showInfo","show info attribute",null,"java.lang.String", MBeanOperationInfo.INFO, null)
        };

        return new ModelMBeanInfoSupport(RequiredModelMBean.class.getName(), "info class requireModelMbean", attrInfos, null, operaInfos, null, null);
    }

    private static Descriptor createDescriptorSupport(String attr){
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
