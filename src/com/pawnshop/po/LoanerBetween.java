package com.pawnshop.po;

public class LoanerBetween {
	private String contractId;
	private String loanerBetweenId;
	private String loanerType;
	private String loanerId;
	
	@Override
	public String toString() {
		return "LoanerBetween [contractId=" + contractId + ", loanerBetweenId="
				+ loanerBetweenId + ", loanerType=" + loanerType
				+ ", loanerId=" + loanerId + "]";
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getLoanerBetweenId() {
		return loanerBetweenId;
	}
	public void setLoanerBetweenId(String loanerBetweenId) {
		this.loanerBetweenId = loanerBetweenId;
	}
	public String getLoanerType() {
		return loanerType;
	}
	public void setLoanerType(String loanerType) {
		this.loanerType = loanerType;
	}
	public String getLoanerId() {
		return loanerId;
	}
	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}
	
	
}
