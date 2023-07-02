package com.frank.activemq.produce;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Topic_Produce {

	@Autowired
	private JmsMessagingTemplate jsmMessagingTemplate;
	
	@Autowired
	private Topic topic;
	
	private int count=1;
	
	
	//間隔時間3秒鐘定投
	@Scheduled(fixedDelay = 3000)
	public void produceMsgScheduled() {
		jsmMessagingTemplate.convertAndSend(topic,"test_topic_"+count);
//		jsmMessagingTemplate.convertAndSend(queue,"TESTTEST"+count);
		count++;
		System.out.println("  produceMsgScheduled  send   ok ....  ");
	}
}
