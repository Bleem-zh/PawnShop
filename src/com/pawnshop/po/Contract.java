package com.pawnshop.po;

import java.sql.Timestamp;

public class Contract {
	private String contractId;
	private String contractNum;
	private String balance;
	private String interest;
	private String term;
	private String percentage;
	private String repaymentSource;
	private String comments;
	private String status;
	private String uId;
	private String productType;
	private String repayMethod;
	private java.sql.Date beginDay;
	private java.sql.Date endDateInput;
	private String grossRepayment;
	private String grossInterest;
	private Timestamp updateTime;
	

	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", contractNum="
				+ contractNum + ", balance=" + balance + ", interest="
				+ interest + ", term=" + term + ", percentage=" + percentage
				+ ", repaymentSource=" + repaymentSource + ", comments="
				+ comments + ", status=" + status + ", uId=" + uId
				+ ", productType=" + productType + ", repayMethod="
				+ repayMethod + ", beginDay=" + beginDay + ", endDateInput="
				+ endDateInput + ", grossRepayment=" + grossRepayment
				+ ", grossInterest=" + grossInterest + ", updateTime="
				+ updateTime + "]";
	}
	
	public String getRepayMethod() {
		return repayMethod;
	}

	public void setRepayMethod(String repayMethod) {
		this.repayMethod = repayMethod;
	}

	public java.sql.Date getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(java.sql.Date beginDay) {
		this.beginDay = beginDay;
	}

	public java.sql.Date getEndDateInput() {
		return endDateInput;
	}

	public void setEndDateInput(java.sql.Date endDateInput) {
		this.endDateInput = endDateInput;
	}

	public String getGrossRepayment() {
		return grossRepayment;
	}

	public void setGrossRepayment(String grossRepayment) {
		this.grossRepayment = grossRepayment;
	}

	public String getGrossInterest() {
		return grossInterest;
	}

	public void setGrossInterest(String grossInterest) {
		this.grossInterest = grossInterest;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getRepaymentSource() {
		return repaymentSource;
	}
	public void setRepaymentSource(String repaymentSource) {
		this.repaymentSource = repaymentSource;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
}
