package com.frank.activemq.produce;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.frank.activemq.dto.Message;

@Component
public class Queue_Produce {

	@Autowired
	private JmsMessagingTemplate jsmMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	private int count=1;
	
	public void produceMsg() {
		
		//轉換+發送
		jsmMessagingTemplate.convertAndSend(queue,"TESTTEST");
		jsmMessagingTemplate.convertAndSend(queue,new Message("001", "Object..."));
		System.out.println("  produceMessage  send   ok   ");
	}
	
	//間隔時間3秒鐘定投
	@Scheduled(fixedDelay = 3000)
	public void produceMsgScheduled() {
		jsmMessagingTemplate.convertAndSend(queue,new Message("@Scheduled00"+count, "Object..."));
//		jsmMessagingTemplate.convertAndSend(queue,"TESTTEST"+count);
		count++;
		System.out.println("  produceMsgScheduled  send   ok ....  ");
	}
}
