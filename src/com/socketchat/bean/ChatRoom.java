package com.socketchat.bean;

import java.sql.Timestamp;

public class ChatRoom {

	private String chatRoomId;
	private String chatRoomName;
	private Timestamp chatRoomCreateDate;
	private String creatorId;
	
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public String getChatRoomName() {
		return chatRoomName;
	}
	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}
	/**
	 * 构建一个可以直接获取String类型的日期
	 * @return String
	 */
	public String chatRoomCreateDateStringTime() {
		if(chatRoomCreateDate == null)
			chatRoomCreateDate = new Timestamp(System.currentTimeMillis());
		return chatRoomCreateDate.toString();
	}
	public Timestamp getChatRoomCreateDate() {
		chatRoomCreateDateStringTime();
		return chatRoomCreateDate;
	}
	public void setChatRoomCreateDate(Timestamp chatRoomCreateDate) {
		this.chatRoomCreateDate = chatRoomCreateDate;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
}
