package com.socketchat.bean;

import java.sql.Timestamp;

public class InviteJoin {
	@Override
	public String toString() {
		return "InviteJoin [inviteJoinId=" + inviteJoinId + ", inviteJoinRoomId=" + inviteJoinRoomId
				+ ", inviteJoinUserId=" + inviteJoinUserId + ", inviteCreateDate=" + inviteCreateDate + "]";
	}
	private String inviteJoinId;
	private String inviteJoinRoomId;
	private String inviteJoinUserId;
	private Timestamp inviteCreateDate;
	
	public String getInviteJoinId() {
		return inviteJoinId;
	}
	public void setInviteJoinId(String inviteJoinId) {
		this.inviteJoinId = inviteJoinId;
	}
	public String getInviteJoinRoomId() {
		return inviteJoinRoomId;
	}
	public void setInviteJoinRoomId(String inviteJoinRoomId) {
		this.inviteJoinRoomId = inviteJoinRoomId;
	}
	public String getInviteJoinUserId() {
		return inviteJoinUserId;
	}
	public void setInviteJoinUserId(String inviteJoinUserId) {
		this.inviteJoinUserId = inviteJoinUserId;
	}
	/**
	 * 构建一个可以直接获取String类型的日期
	 * @return String
	 */
	public String inviteCreateDateStringTime() {
		if(inviteCreateDate == null)
			inviteCreateDate = new Timestamp(System.currentTimeMillis());
		return inviteCreateDate.toString();
	}
	public Timestamp getInviteCreateDate() {
		inviteCreateDateStringTime();
		return inviteCreateDate;
	}
	public void setInviteCreateDate(Timestamp inviteCreateDate) {
		this.inviteCreateDate = inviteCreateDate;
	}
}
