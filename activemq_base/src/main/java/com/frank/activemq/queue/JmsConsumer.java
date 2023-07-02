package com.frank.activemq.queue;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsConsumer {
	
//	public static final String ACTIVEMQ_URL = "tcp://192.168.47.129:61616";
	public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
	public static final String QUEUE_NAME = "queue01";

	public static void main(String[] args) throws JMSException, IOException {
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
		Queue queue = session.createQueue(QUEUE_NAME);
		
		//5.創建消費者
		MessageConsumer messageConsumer = session.createConsumer(queue);
		
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
//		
		//通過監聽的方式來消費消息
		//異步非阻塞方式(監聽器onMessage())
		//訂閱者或接收者通過MmessageConsumer的setMessageListener(MessageListener listener)註冊一個消息監聽器
		//當消息到達之後，系統自動調用監聽器MessageListener 的onMessage(Message message)方法
		messageConsumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				if(null != message && message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					
					try {
						System.out.println("***消費者接收到消息:"+textMessage.getText());
						System.out.println("***消費者接收到消息屬性:"+textMessage.getStringProperty("attribute"));
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				if(null != message && message instanceof MapMessage) {
					MapMessage mapMessage = (MapMessage) message;
					
					try {
						System.out.println("***消費者接收到map消息:"+mapMessage.getString("type"));
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		System.in.read();
		
		messageConsumer.close();
		session.close();
		connection.close();
		
		
		/**
		 * 1.先生產，    只啟動1號消費者，問題:1號消費者還能消費消息嗎?
		 * Y
		 * 2.先生產，    先啟動1號消費者，再啟動2號消費者 ，問題:2號消費者還能消費消息嗎?
		 * 		2.1 1號可以消費 Y
		 *      2.2 2號可以消費嗎? N
		 * 
		 * 3. 先啟動2個消費者，在生產6條消息，請問消費情況如何?
		 *  3.1 2個消費者都有6條 N
		 *  3.2先到先得，6條權給給一個 N
		 *  3.3一人一半  Y
		 * */
	}
}
