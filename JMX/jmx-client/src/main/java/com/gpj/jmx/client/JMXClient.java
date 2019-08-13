package com.gpj.jmx.client;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @className JMXClient
 * @description TODO
 * @author GPJ
 * @date 2019/7/25 18:43
 * @version 1.0
 **/
public class JMXClient {
    public static void main(String[] args) {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8686/jmxrmi");
            JMXConnector jmxConnector = JMXConnectorFactory.connect(url,null);
            MBeanServerConnection mbServer = jmxConnector.getMBeanServerConnection();
            ObjectName mbeanName = new ObjectName("jmxBean:name=info");

            String domains[] = mbServer.getDomains();
            for (int i = 0; i < domains.length; i++) {
                System.out.println("Domain[" + i +"] = " + domains[i]);
            }

            System.out.println("MBean count = " + mbServer.getMBeanCount());

            //set value
            mbServer.setAttribute(mbeanName, new Attribute("InfoName", "gtestInfo"));
            //get value
            System.out.println("Name = " + mbServer.getAttribute(mbeanName, "InfoName"));

            /*
            InfoMBean proxy = (InfoMBean) MBeanServerInvocationHandler.newProxyInstance(mbServer, mbeanName, InfoMBean.class, false);
            System.out.println(proxy.printHello());
            */
            // showInfo方法
            System.out.println(mbServer.invoke(mbeanName, "showInfo", null, null));

            jmxConnector.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
