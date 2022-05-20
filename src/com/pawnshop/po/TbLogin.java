package com.pawnshop.po;

import java.sql.Timestamp;

/**
 * 登录记录
 * @author Administrator
 *
 */
public class TbLogin {
	private String loginId;
	private String loginIp;
	private String uId;
	private String userName;
	private Timestamp createTime;
	
	@Override
	public String toString() {
		return "TbLogin [loginId=" + loginId + ", loginIp=" + loginIp
				+ ", uId=" + uId + ", userName=" + userName + ", createTime="
				+ createTime + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
