package com.gpj.spring;

import com.gpj.spring.bean.LearnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestApplication {
	public static void main(String[] args) {
		// 用我们的配置文件来启动一个 ApplicationContext
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

		System.out.println("context 启动成功");

		// 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
		LearnBean bean = context.getBean(LearnBean.class);
		// 这句将输出: hello world
		System.out.println(bean.info());

	}
}
