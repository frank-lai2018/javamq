package com.frank.activemq.config;

import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@Component
@EnableJms//開啟JMS註解
public class ConfigBean {
	
	private String myTopic="boot-activemq-topic";
	
	@Bean
	public Topic topic() {
		return new ActiveMQTopic(this.myTopic);
	}
}
