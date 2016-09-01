package com.socketchat.bean;

public class FriendTab {
	
	@Override
	public String toString() {
		return "FriendTab [friendTabId=" + friendTabId + ", friendAId=" + friendAId + ", friendBId=" + friendBId + "]";
	}
	private String friendTabId;
	private String friendAId;
	private String friendBId;
	public String getFriendTabId() {
		return friendTabId;
	}
	public void setFriendTabId(String friendTabId) {
		this.friendTabId = friendTabId;
	}
	public String getFriendAId() {
		return friendAId;
	}
	public void setFriendAId(String friendAId) {
		this.friendAId = friendAId;
	}
	public String getFriendBId() {
		return friendBId;
	}
	public void setFriendBId(String friendBId) {
		this.friendBId = friendBId;
	}
}
