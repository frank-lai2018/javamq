package com.frank.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		if(null != message && message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("監聽器3333:"+textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
	}

}
