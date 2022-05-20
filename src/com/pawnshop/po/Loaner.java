package com.pawnshop.po;

import java.sql.Timestamp;

public class Loaner {
	private String loanerId;
	private String name;
	private String gender;
	private String age;
	private String register;
	private String marriage;
	private String credit;
	private String loanerType;
	private String citizenID;
	private String telphoneNum;
	private Timestamp updateTime;
	
	@Override
	public String toString() {
		return "Loaner [loanerId=" + loanerId + ", name=" + name + ", gender="
				+ gender + ", age=" + age + ", register=" + register
				+ ", marriage=" + marriage + ", credit=" + credit
				+ ", loanerType=" + loanerType + ", citizenID=" + citizenID
				+ ", telphoneNum=" + telphoneNum + ", updateTime=" + updateTime
				+ "]";
	}

	public String getTelphoneNum() {
		return telphoneNum;
	}

	public void setTelphoneNum(String telphoneNum) {
		this.telphoneNum = telphoneNum;
	}

	public void setCitizenID(String citizenID) {
		this.citizenID = citizenID;
	}

	public String getCitizenID() {
		return citizenID;
	}

	public void setCitizenId(String citizenID) {
		this.citizenID = citizenID;
	}

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getLoanerType() {
		return loanerType;
	}

	public void setLoanerType(String loanerType) {
		this.loanerType = loanerType;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	

}
