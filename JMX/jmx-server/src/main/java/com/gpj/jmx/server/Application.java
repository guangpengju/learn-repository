package com.gpj.jmx.server;

import com.gpj.jmx.server.agent.Agent;

/**
 * @className Application
 * @description TODO
 * @author GPJ
 * @date 2019/8/13 14:19
 * @version 1.0
 **/
public class Application {
    public static void main(String[] args){
//        Agent.connectByJconsole(); // Jconsole连接

//        Agent.connectByClient(); // client连接

//        Agent.connectUseListener(); // MBean间通信

//        Agent.connectDynamicMBean(); // 动态Mbean

        Agent.connectModelMBean(); // ModelMBean
    }
}
