package com.pawnshop.po;

import java.sql.Timestamp;

public class TbReviewBetween {
	private int rbId;
	private String contractId;
	private String rId;
	private Timestamp updateTime;
	@Override
	public String toString() {
		return "TbReviewBetween [rbId=" + rbId + ", contractId=" + contractId
				+ ", rId=" + rId + ", updateTime=" + updateTime + "]";
	}

	public int getRbId() {
		return rbId;
	}
	public void setRbId(int rbId) {
		this.rbId = rbId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
