package com.frank.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQ_Consumer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public static void main(String[] args) throws JMSException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		SpringMQ_Consumer consumer = (SpringMQ_Consumer) ctx.getBean("springMQ_Consumer");
		
		TextMessage textMessage = (TextMessage)consumer.jmsTemplate.receive();
//		
//		System.out.println("消費者得到消息...."+textMessage.getText());
		consumer.jmsTemplate.setReceiveTimeout(3000);
//		consumer.jmsTemplate.set
		String returnValue = (String) consumer.jmsTemplate.receiveAndConvert();
		System.out.println("消費者得到消息11...."+returnValue);
		
		//關閉Spring
//		((ConfigurableApplicationContext)ctx).close();
	}
}
