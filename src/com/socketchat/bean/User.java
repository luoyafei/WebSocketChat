package com.socketchat.bean;

import java.sql.Timestamp;

public class User {
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof User) {
			if(this.userId.equals(((User) obj).getUserId())) {
				return true;
			}
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName + ", password=" + password + ", remoteIp=" + remoteIp
				+ ", userPicture=" + userPicture + ", createDate=" + createDate + "]";
	}
	private String userId;
	private String nickName;
	private String password;
	private String remoteIp;
	private String userPicture;
	private Timestamp createDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	/**
	 * 构建一个可以直接获取String类型的日期
	 * @return String
	 */
	public String userCreateDateStringTime() {
		if(createDate == null)
			createDate = new Timestamp(System.currentTimeMillis());
		return createDate.toString();
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		userCreateDateStringTime();
		this.createDate = createDate;
	}
	
	
}
