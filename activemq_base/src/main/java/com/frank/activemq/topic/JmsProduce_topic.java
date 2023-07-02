package com.frank.activemq.topic;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsProduce_topic {
	
	public static final String ACTIVEMQ_URL = "tcp://192.168.47.129:61616";
	public static final String TOPIC_NAME = "topic01";
	
	public static void main(String[] args) throws JMSException {
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
		
		//5.創建消息的生產者
		MessageProducer messageProducer = session.createProducer(topic);
		
		//6.通過使用 MessageProducer 生產3條消息發送到MQ隊列裡面
		for(int i = 1 ;i<=3;i++) {
			
			//7.創建消息
			TextMessage textMessage = session.createTextMessage("msg----"+i);//理解為一個字串
			
			//8.通過MessageProducer發送給MQ
			messageProducer.send(textMessage);
		}
		
		//9.關閉資源
		messageProducer.close();
		session.close();
		connection.close();
		
		System.out.println("消息發布完成");
	}
}
