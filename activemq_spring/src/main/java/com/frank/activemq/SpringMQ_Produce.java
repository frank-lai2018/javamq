package com.frank.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQ_Produce {

	@Autowired
	private JmsTemplate jsmTemplate;
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		SpringMQ_Produce produce = (SpringMQ_Produce) ctx.getBean("springMQ_Produce");
		
		produce.jsmTemplate.send(session -> {
			return session.createTextMessage("****Spring和activeMQ的整合case33333*****");
		});
		
		System.out.println("send task OK....");
		
		//關閉Spring
//		((ConfigurableApplicationContext)ctx).close();
	}
}
