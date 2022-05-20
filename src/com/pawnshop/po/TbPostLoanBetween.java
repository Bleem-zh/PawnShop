package com.pawnshop.po;

import java.sql.Timestamp;

public class TbPostLoanBetween {

	private String plbId;//主键
	private String contractId;//合同表主键
	private String plrId;//贷后更新记录表主键
	private Timestamp updateTime;
	private Integer COUNT;
	
	@Override
	public String toString() {
		return "TbPostLoanBetween [plbId=" + plbId + ", contractId="
				+ contractId + ", plrId=" + plrId + ", updateTime="
				+ updateTime + "]";
	}

	public Integer getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(Integer cOUNT) {
		COUNT = cOUNT;
	}

	public String getPlbId() {
		return plbId;
	}

	public void setPlbId(String plbId) {
		this.plbId = plbId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getPlrId() {
		return plrId;
	}

	public void setPlrId(String plrId) {
		this.plrId = plrId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
