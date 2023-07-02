package com.frank.activemq.consumer;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;



@Component
public class Topic_Consumer {
	
	@JmsListener(destination = "boot-activemq-topic")
	public void receive(TextMessage textmessage) throws JMSException {
			System.out.println("textMessage:"+textmessage.getText());
//			System.out.println("textMessage:"+message.getBody(Message.class));
	}
}
