package com.frank.activemq.dto;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = -5292603378214046021L;
	private String id;
	private String msg;
	
	
	
	public Message(String id, String msg) {
		super();
		this.id = id;
		this.msg = msg;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	@Override
	public String toString() {
		return "Message [id=" + id + ", msg=" + msg + "]";
	}
	
	
}
