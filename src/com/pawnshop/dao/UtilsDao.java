package com.pawnshop.dao;

import com.pawnshop.po.TbContractIndex;

public interface UtilsDao {
	public TbContractIndex getContractIndex(TbContractIndex tbContractIndex);
	
	public void insertContractIndex(TbContractIndex tbContractIndex);
	
	public void updateContractIndex(TbContractIndex tbContractIndex);
}
