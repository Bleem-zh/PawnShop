package com.pawnshop.po;

import java.sql.Timestamp;

public class TbLoginRecord {
	private int lrId;
	private String loginIp;
	private String uId;
	private Timestamp updateTime;
	
	@Override
	public String toString() {
		return "TbLoginRecord [lrId=" + lrId + ", loginIp=" + loginIp
				+ ", uId=" + uId + ", updateTime=" + updateTime + "]";
	}
	public int getLrId() {
		return lrId;
	}
	public void setLrId(int lrId) {
		this.lrId = lrId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
