package com.socketchat.bean;

import java.sql.Timestamp;

public class ChatRoom {

	
	@Override
	public String toString() {
		return "ChatRoom [chatRoomId=" + chatRoomId + ", chatRoomName=" + chatRoomName + ", creatorId=" + creatorId
				+ ", chatRoomCreateDate=" + chatRoomCreateDate + ", chatRoomCover=" + chatRoomCover + ", chatBrief="
				+ chatBrief + ", chatRoomPassword=" + chatRoomPassword + ", needPassword=" + needPassword + "]";
	}
	private String chatRoomId;
	private String chatRoomName;
	private String creatorId;
	private Timestamp chatRoomCreateDate;
	private String chatRoomCover;
	private String chatBrief;
	private String chatRoomPassword;
	private String needPassword;
	
	
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
	
	public String getChatRoomCover() {
		return chatRoomCover;
	}
	public void setChatRoomCover(String chatRoomCover) {
		this.chatRoomCover = chatRoomCover;
	}
	public String getChatBrief() {
		return chatBrief;
	}
	public void setChatBrief(String chatBrief) {
		this.chatBrief = chatBrief;
	}
	public String getChatRoomPassword() {
		return chatRoomPassword;
	}
	public void setChatRoomPassword(String chatRoomPassword) {
		this.chatRoomPassword = chatRoomPassword;
	}
	public String getNeedPassword() {
		return needPassword;
	}
	public void setNeedPassword(String needPassword) {
		this.needPassword = needPassword;
	}
}
