package com.pawnshop.po;

import java.sql.Timestamp;

public class TbContractBetween {
	private String contractId;
	private String proBetId;
	private String mortgageType;
	private String productId;
	private Timestamp updateTime;
	
	
	@Override
	public String toString() {
		return "PropertyBetween [contractId=" + contractId + ", proBetId="
				+ proBetId + ", mortgageType=" + mortgageType + ", productId="
				+ productId + ", updateTime=" + updateTime + "]";
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getProBetId() {
		return proBetId;
	}
	public void setProBetId(String proBetId) {
		this.proBetId = proBetId;
	}
	public String getMortgageType() {
		return mortgageType;
	}
	public void setMortgageType(String mortgageType) {
		this.mortgageType = mortgageType;
	}
	public String getproductId() {
		return productId;
	}
	public void setproductId(String productId) {
		this.productId = productId;
	}
	
}
