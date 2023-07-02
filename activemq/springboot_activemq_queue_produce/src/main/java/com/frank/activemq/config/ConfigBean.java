package com.frank.activemq.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@Component
@EnableJms//開啟JMS註解
public class ConfigBean {
	
//	@Value("${frank.myqueue}")
	private String myQueue="boot-activemq-queue";
	
	@Bean
	public ActiveMQQueue queue() {
		return new ActiveMQQueue(this.myQueue);
	}
}
