package com.frank.activemq.topic;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsConsumer_topic {
	
	public static final String ACTIVEMQ_URL = "tcp://192.168.47.129:61616";
	public static final String TOPIC_NAME = "topic01";

	public static void main(String[] args) throws JMSException, IOException {
		System.out.println("我是消費者3...........");
		//1.創建連接工廠，按照給定的URL地址，採用默認用戶名和密碼
		ActiveMQConnectionFactory actuActiveMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		
		//2.通過連接工廠，獲得連接connection並開啟訪問
		Connection connection = actuActiveMQConnectionFactory.createConnection();
		connection.start();
		
		//3.創建session
		//兩個參數，第一個叫事務/第二個叫簽收
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		
		//4.創建目的地(具體是對列還是主題topic)
//		Destination destination = session.createQueue(QUEUE_NAME);
		Topic topic = session.createTopic(TOPIC_NAME);
		
		//5.創建消費者
		MessageConsumer messageConsumer = session.createConsumer(topic);
		
//		while(true) {
//			
//			/*
//			 * 同步阻塞方式(receive())
//			 * 訂閱者或接收者調用MessageConsumer的receive()方法來接收消息，receive方法在能夠接收到消息之前(或超時之前)將一直堵塞
//			 * */
//			TextMessage textMessage = (TextMessage) messageConsumer.receive();//一直等
////			TextMessage textMessage = (TextMessage) messageConsumer.receive(1000);//等待一秒後離開
//			if(null != textMessage) {
//				System.out.println("***消費者接收到消息:"+textMessage.getText());
//			}else{
//				break;
//			}
//		}
		
		//通過監聽的方式來消費消息
		//異步非阻塞方式(監聽器onMessage())
		//訂閱者或接收者通過MmessageConsumer的setMessageListener(MessageListener listener)註冊一個消息監聽器
		//當消息到達之後，系統自動調用監聽器MessageListener 的onMessage(Message message)方法
//		messageConsumer.setMessageListener(new MessageListener() {
//			
//			public void onMessage(Message message) {
//				if(null != message && message instanceof TextMessage) {
//					TextMessage textMessage = (TextMessage) message;
//					
//					try {
//						System.out.println("***消費者接收到消息:"+textMessage.getText());
//					} catch (JMSException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		});
		messageConsumer.setMessageListener(message -> {
				if(null != message && message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					
					try {
						System.out.println("***消費者接收到消息:"+textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
		});
		
		System.in.read();
		
		messageConsumer.close();
		session.close();
		connection.close();
		
		
	}
}
