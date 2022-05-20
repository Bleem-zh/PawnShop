package com.pawnshop.po;

import java.sql.Timestamp;

public class TbReviewRecord {
	private String rId;
	

	private String rComments;
	private String rStatus;
	private String uId;
	private String contractId;
	private Timestamp updateTime;
	


	@Override
	public String toString() {
		return "TbReviewRecord [rId=" + rId + ", rComments=" + rComments
				+ ", rStatus=" + rStatus + ", uId=" + uId + ", contractId="
				+ contractId + ", updateTime=" + updateTime + "]";
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getrComments() {
		return rComments;
	}

	public void setrComments(String rComments) {
		this.rComments = rComments;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
