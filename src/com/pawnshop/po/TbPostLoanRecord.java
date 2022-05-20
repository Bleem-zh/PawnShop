package com.pawnshop.po;

import java.sql.Timestamp;

public class TbPostLoanRecord {
	
	private String plrId;			// 主键
	private String contractId;
	private String beforeStatus;	//原状态
	private String updateReason;	//更新原因
	private String plComments;		//更新说明
	private String plStatus;		//更新后状态
	private String uId;				//更新人
	private Timestamp updateTime;
	
	@Override
	public String toString() {
		return "TbPostLoanRecord [plrId=" + plrId + ", contractId="
				+ contractId + ", beforeStatus=" + beforeStatus
				+ ", updateReason=" + updateReason + ", plComments="
				+ plComments + ", plStatus=" + plStatus + ", uId=" + uId
				+ ", updateTime=" + updateTime + "]";
	}

	public String getBeforeStatus() {
		return beforeStatus;
	}

	public void setBeforeStatus(String beforeStatus) {
		this.beforeStatus = beforeStatus;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	public String getPlrId() {
		return plrId;
	}

	public void setPlrId(String plrId) {
		this.plrId = plrId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getPlStatus() {
		return plStatus;
	}

	public void setPlStatus(String plStatus) {
		this.plStatus = plStatus;
	}

	public String getPlComments() {
		return plComments;
	}

	public void setPlComments(String plComments) {
		this.plComments = plComments;
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
