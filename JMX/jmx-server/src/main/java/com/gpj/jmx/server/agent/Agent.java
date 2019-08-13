package com.gpj.jmx.server.agent;

import com.gpj.jmx.server.listener.Cook;
import com.gpj.jmx.server.listener.Diners;
import com.gpj.jmx.server.listener.DinersLisenter;
import com.gpj.jmx.server.mbean.dynamic.DynamicInfo;
import com.gpj.jmx.server.mbean.model.GInfo;
import com.gpj.jmx.server.mbean.model.ModelMBeanUtils;
import com.gpj.jmx.server.mbean.standard.Info;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @className InfoAgent
 * @description TODO
 * @author GPJ
 * @date 2019/7/25 18:37
 * @version 1.0
 **/
public class Agent {
    private static MBeanServer server = ManagementFactory.getPlatformMBeanServer();

    /**
     * jconsole连接
     */
    public static void connectByJconsole(){
        try {
            boolean result = register(new Info());
            if(result){
                Thread.sleep(1000 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端连接
     */
    public static void connectByClient(){
        try {
            boolean register = register(new Info());

            if(register){
                Registry registry = LocateRegistry.createRegistry(8686);
                JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8686/jmxrmi");
                JMXConnectorServer jmxServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, server);

                jmxServer.start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MBean使用监听
     */
    public static void connectUseListener(){
        try {
            Diners diners = new Diners();
            Cook cook = new Cook();
            if(register(cook) && register(diners)){
                cook.addNotificationListener(new DinersLisenter(), null, diners);

                Thread.sleep(1000 * 1000);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态MBean
     */
    public static void connectDynamicMBean(){
        try {
            boolean result = register(new DynamicInfo());
            if(result){
                Thread.sleep(1000 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ModelMBean
     */
    public static void connectModelMBean(){
        try {
            boolean result = register(ModelMBeanUtils.createModlerMBean(GInfo.class));
            if(result){
                Thread.sleep(1000 * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean register(Object bean){
        try {
            ObjectName objectName = new ObjectName("jmxBean:name=" + bean.getClass().getName());
            server.registerMBean(bean, objectName);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
