package com.pawnshop.po;

import java.sql.Timestamp;

public class TbProperty {
	
	private String propertyId;
	private String communityName;
	private String communityAddress;
	private String functional;
	private String area;
	private String proType;
	private String decoration;
	private String status;
	private String years;
	private String obligee;
	private String obligeeRelation;
	private String olderChildren;
	private String registe;
	private String source;
	private String procount;
	private String univalent;
	private String mortgage;
	private String percentage;
	private Timestamp updateTime;
	
	
	

	
	@Override
	public String toString() {
		return "TbProperty [propertyId=" + propertyId + ", communityName="
				+ communityName + ", communityAddress=" + communityAddress
				+ ", functional=" + functional + ", area=" + area
				+ ", proType=" + proType + ", decoration=" + decoration
				+ ", status=" + status + ", years=" + years + ", obligee="
				+ obligee + ", obligeeRelation=" + obligeeRelation
				+ ", olderChildren=" + olderChildren + ", registe=" + registe
				+ ", source=" + source + ", procount=" + procount
				+ ", univalent=" + univalent + ", mortgage=" + mortgage
				+ ", percentage=" + percentage + ", updateTime=" + updateTime
				+ "]";
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getpropertyId() {
		return propertyId;
	}
	public void setpropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getCommunityAddress() {
		return communityAddress;
	}
	public void setCommunityAddress(String communityAddress) {
		this.communityAddress = communityAddress;
	}
	public String getFunctional() {
		return functional;
	}
	public void setFunctional(String functional) {
		this.functional = functional;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getObligee() {
		return obligee;
	}
	public void setObligee(String obligee) {
		this.obligee = obligee;
	}
	public String getObligeeRelation() {
		return obligeeRelation;
	}
	public void setObligeeRelation(String obligeeRelation) {
		this.obligeeRelation = obligeeRelation;
	}
	public String getOlderChildren() {
		return olderChildren;
	}
	public void setOlderChildren(String olderChildren) {
		this.olderChildren = olderChildren;
	}
	public String getRegiste() {
		return registe;
	}
	public void setRegiste(String registe) {
		this.registe = registe;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getProcount() {
		return procount;
	}

	public void setProcount(String procount) {
		this.procount = procount;
	}
	public String getUnivalent() {
		return univalent;
	}
	public void setUnivalent(String univalent) {
		this.univalent = univalent;
	}
	public String getMortgage() {
		return mortgage;
	}
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
