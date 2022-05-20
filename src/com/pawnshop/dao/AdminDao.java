package com.pawnshop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pawnshop.po.Jewellery;
import com.pawnshop.po.User;
import com.pawnshop.po.Contract;

public interface AdminDao {

	public List<Jewellery> findJList(@Param("page")int page,
									@Param("limit")int limit);
	
	public List<Object> findReviewJList(@Param("page")int page,
			@Param("limit")int limit, @Param("uId")String uId, @Param("balance")String balance);
	
	public Jewellery findJInfo(int jid);

	public void changeJstate(@Param("state")String state,@Param("jid")int jid);
	
	public List<User> findUList(@Param("page")int page,
			@Param("limit")int limit);
	
	public User findUInfo(int uid);
	
	int countUser();
	
	public void updateUser(@Param("user") User user);
	
	public List<Contract> findAProList(@Param("page")int page,
											@Param("limit")int limit,
											@Param("uId")String uId, 
											@Param("balance")String balance,
											@Param("productType")String productType);

	public int countPropertyReview(@Param("uId")String uId);

	/**
	 * 通过合同id获取提交之后的房产界面信息
	 * @param contractId
	 * @return
	 */
	public Object getPropertyPage(String contractId);
	
	/**
	 *  获取贷后管理界面列表信息
	 * @param page
	 * @param limit
	 * @param uId
	 * @param contractId 模糊查询条件
	 * @return
	 */
	public List<Object> gePostLoanList(@Param("page")int page,
										@Param("limit")int limit,
										@Param("uId")String uId,
										@Param("contractId")String contractId);
	
	/**
	 * 统计贷后管理界面列表信息总数
	 * @param uId
	 * @return
	 */
	public int countPostLoanList(@Param("uId")String uId);

	/**
	 * 统计历史借款总数
	 * @param uId
	 * @return
	 */
	public int countHistoryPostLoanList(@Param("uId")String uId);

	/**
	 * 获取历史借款列表界面数据
	 * @param page
	 * @param limit
	 * @param uId
	 * @param contractId
	 * @return
	 */
	public List<Object> geHistoryPostLoanList(@Param("page")int page,
			@Param("limit")int limit,
			@Param("uId")String uId,
			@Param("contractId")String contractId);
	
}

