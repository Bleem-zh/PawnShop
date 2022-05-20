package com.pawnshop.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pawnshop.po.Jewellery;
import com.pawnshop.po.User;
import com.pawnshop.po.Contract;

public interface AdminService {

	public List<Jewellery> findJList(int page,int limit);
	
	/**
	 * 查询未提交的产品列表信息
	 * @param page
	 * @param limit
	 * @param uId 
	 * @param balance 
	 * @param productType 
	 * @return
	 */
	public List<Contract> findAProList(int page,int limit, String uId, String balance, String productType);
	
	public List<Object> findReviewJList(int page,int limit,String uId, String balance);
	
	public Jewellery findJInfo(int jid);

	public void changeJstate(String state,int jid);

	public List<User> findUList(int page, int limit);
}
