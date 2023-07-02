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
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsConsumer_topic_persist {
	
	public static final String ACTIVEMQ_URL = "tcp://192.168.47.129:61616";
	public static final String TOPIC_NAME = "topic_persist";

	public static void main(String[] args) throws JMSException, IOException {
		System.out.println("frank...........");
		//1.創建連接工廠，按照給定的URL地址，採用默認用戶名和密碼
		ActiveMQConnectionFactory actuActiveMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		
		//2.通過連接工廠，獲得連接connection並開啟訪問
		Connection connection = actuActiveMQConnectionFactory.createConnection();
		connection.setClientID("frank");//誰訂閱了
		
		//3.創建session
		//兩個參數，第一個叫事務/第二個叫簽收
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		
		
		//4.創建目的地(具體是對列還是主題topic)
//		Destination destination = session.createQueue(QUEUE_NAME);
		Topic topic = session.createTopic(TOPIC_NAME);
		
		//創建持久化的訂閱主題
		TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark...");

		connection.start();
							
		
		Message message = topicSubscriber.receive();
		
		while(null != message) {
			TextMessage textMessage = (TextMessage)message;
			System.out.println("*****收到的持久化topic:"+textMessage.getText());
			message = topicSubscriber.receive(5000);
		}
		
		
		
		session.close();
		connection.close();
		
		/*
		 * 1.一定要先運行一次消費者，等於向MQ註冊，類似我訂閱了這個主題
		 * 2.然後在運行生產者發送信息
		 * 3.此時無論消費者是否在線，都會接收到，不在線的話，下次連接的時候，會把沒有收過的消息都接收下來
		 * 
		 * */
	}
}
