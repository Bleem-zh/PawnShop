package com.pawnshop.po;

import java.sql.Timestamp;

public class TbContractIndex {
	
	private String indexDate;
	private String productType;
	private String sortIndex;
	private Timestamp updateTime;
	
	@Override
	public String toString() {
		return "TbContractIndex [indexDate=" + indexDate + ", productType="
				+ productType + ", sortIndex=" + sortIndex + ", updateTime="
				+ updateTime + "]";
	}

	public String getIndexDate() {
		return indexDate;
	}

	public void setIndexDate(String indexDate) {
		this.indexDate = indexDate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
