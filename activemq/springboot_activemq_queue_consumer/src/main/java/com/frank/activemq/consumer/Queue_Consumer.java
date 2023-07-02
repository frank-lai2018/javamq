package com.frank.activemq.consumer;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;



@Component
public class Queue_Consumer {
	
	@JmsListener(destination = "boot-activemq-queue")
	public void receive(ObjectMessage objectmessage) throws JMSException {
			System.out.println("textMessage:"+objectmessage.getObject());
//			System.out.println("textMessage:"+message.getBody(Message.class));
	}
}
