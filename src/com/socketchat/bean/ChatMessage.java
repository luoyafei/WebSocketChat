package com.socketchat.bean;

import java.sql.Timestamp;


public class ChatMessage {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChatMessage [chatMessageId=" + chatMessageId + ", chatMessage=" + chatMessage + ", fromUserId="
				+ fromUserId + ", toUserId=" + toUserId + ", toChatRoomId=" + toChatRoomId + ", messageSendTime="
				+ messageSendTime + ", needRead=" + needRead + "]";
	}
	private String chatMessageId;
	private String chatMessage;
	private String fromUserId;
	private String toUserId;
	private String toChatRoomId = "0";//默认的聊天室是没有的,0
	private Timestamp messageSendTime;
	private int needRead = 0;//默认是未读，等读取后转换为1，为已读状态
	
	public String getChatMessageId() {
		return chatMessageId;
	}
	public void setChatMessageId(String chatMessageId) {
		this.chatMessageId = chatMessageId;
	}
	public String getChatMessage() {
		return chatMessage;
	}
	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}
	
	/**
	 * 构建一个可以直接获取String类型的日期
	 * @return String
	 */
	public String messageSendTimeStringTime() {
		if(messageSendTime == null)
			messageSendTime = new Timestamp(System.currentTimeMillis());
		return messageSendTime.toString();
	}
	public Timestamp getMessageSendTime() {
		messageSendTimeStringTime();
		return messageSendTime;
	}
	public void setMessageSendTime(Timestamp messageSendTime) {
		this.messageSendTime = messageSendTime;
	}
	public int getNeedRead() {
		return needRead;
	}
	public void setNeedRead(int needRead) {
		this.needRead = needRead;
	}
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getToChatRoomId() {
		return toChatRoomId;
	}
	public void setToChatRoomId(String toChatRoomId) {
		if(toChatRoomId == null)
			this.toChatRoomId = "0";
	}
}
