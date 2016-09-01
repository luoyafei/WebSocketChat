package com.socketchat.bean;

import java.sql.Timestamp;

public class ApplyAdd {
	
	@Override
	public String toString() {
		return "ApplyAdd [applyAddId=" + applyAddId + ", applyAddUser=" + applyAddUser + ", toUser=" + toUser
				+ ", applyAddReason=" + applyAddReason + ", applyAddCreateDate=" + applyAddCreateDate + "]";
	}
	private String applyAddId;
	private String applyAddUser;
	private String toUser;
	private String applyAddReason;
	private Timestamp applyAddCreateDate;
	
	public String getApplyAddId() {
		return applyAddId;
	}
	public void setApplyAddId(String applyAddId) {
		this.applyAddId = applyAddId;
	}
	public String getApplyAddUser() {
		return applyAddUser;
	}
	public void setApplyAddUser(String applyAddUser) {
		this.applyAddUser = applyAddUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getApplyAddReason() {
		return applyAddReason;
	}
	public void setApplyAddReason(String applyAddReason) {
		this.applyAddReason = applyAddReason;
	}
	/**
	 * 构建一个可以直接获取String类型的日期
	 * @return String
	 */
	public String applyAddCreateDateStringTime() {
		if(applyAddCreateDate == null)
			applyAddCreateDate = new Timestamp(System.currentTimeMillis());
		return applyAddCreateDate.toString();
	}
	public Timestamp getApplyAddCreateDate() {
		applyAddCreateDateStringTime();
		return applyAddCreateDate;
	}
	public void setApplyAddCreateDate(Timestamp applyAddCreateDate) {
		this.applyAddCreateDate = applyAddCreateDate;
	}
}
