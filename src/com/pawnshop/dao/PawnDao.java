package com.pawnshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pawnshop.po.Bag;
import com.pawnshop.po.Contract;
import com.pawnshop.po.Diamond;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.Loaner;
import com.pawnshop.po.LoanerBetween;
import com.pawnshop.po.Metal;
import com.pawnshop.po.TbContractBetween;
import com.pawnshop.po.Stone;
import com.pawnshop.po.TbPostLoanRecord;
import com.pawnshop.po.TbProperty;
import com.pawnshop.po.TbReviewRecord;
import com.pawnshop.po.Watch;

public interface PawnDao {

	int insertMetal(Metal metal);
	
	int insertJewellery(Jewellery jewellery);
	
	int insertBag(Bag bag);
	
	int insertDiamond(Diamond diamond);
	
	int insertStone(Stone stone);
	
	int insertWatch(Watch watch);
	
	int updateJewelleryState(int jid,String state);
	
	List<Jewellery> findReviewList();

	int saveJphoto(Jewellery jewellery);

	List<Loaner> findLoanerList(Loaner loaner,int page,int limit);
	
	//新增房贷合同
	int insertContract(Contract contract);
	
	int findAProCount(@Param("uId")String uId,@Param("productType")String productType);

	int insertLoanBetween(LoanerBetween loanerBetween);

	List<Object> findContratLoaner(@Param("contractId")String contractId, @Param("loanerType")String loanerType);

	Loaner findLerByCId(String citizenID);

	int insertLoaner(Loaner loaner);

	int countLoaners();

	//通过借款人中间表获取借款人
	List<Object> findLoanerByCId(LoanerBetween loanerBetween);

	int delLoanerBetween(String loanerBetweenId);

	int insertProperty(TbProperty property);

	List<Object> getProductByConId(Map<String, String> parameters);

	void delPropertyBetween(String proBetId);

	int getCountProperty();

	List<TbProperty> getPropertyList(@Param("page")int page, @Param("limit")int limit, @Param("communityName")String communityName);

	void inserProBetween(TbContractBetween propertyBetween);

	List<TbReviewRecord> getReviewRecordList(@Param("contractId")String contractId);

	List<TbPostLoanRecord> getPostLoanRecord(@Param("contractId")String contractId,
															@Param("page")Integer page, 
															@Param("limit")Integer limit);

	int countPostLoanRecord(String contractId);

	/**
	 * 更新借款人信息
	 * @param loaner
	 * @return
	 */
	int updateLoanerInfo(Loaner loaner);

	/**
	 * 查询借款人在途的典当
	 * @param loanerId
	 * @return
	 */
	int findLoanerPawnIng(@Param("loanerId")String loanerId);

	/**
	 * 根据合同id获取审核通过后的借款人列表
	 * @param loanerBetween
	 * @return
	 */
	List<Object> getApproveLoaner(LoanerBetween loanerBetween);
	
	/**
	 * 根据合同id获取申请的借款人列表
	 * @param loanerBetween
	 * @return
	 */
	List<HashMap<String, String>> getApplyLoaner(String contractId);

	/**
	 * 通过合同id和抵押类型获取审核通过后的产品信息 
	 * @param contractId
	 * @param mortgageType
	 * @return
	 */
	List<Object> getApproveProduct(@Param("backupTb")String backupTb,
								@Param("primaryKeyName")String primaryKeyName,
								@Param("contractId")String contractId,
								@Param("mortgageType")String mortgageType);
	
	/**
	 * 通过合同id产品信息 
	 * @param contractId
	 * @param mortgageType
	 * @return
	 */
/*	List<Object> getApplyProduct(@Param("backupTb")String backupTb,
			@Param("primaryKeyName")String primaryKeyName,
			@Param("contractId")String contractId);
*/	List<HashMap<String, String>> getApplyProduct(Map<String, String> parameterMap);

	/**
	 * 根据合同id删除合同
	 * @param contractId
	 */
	void delContract(String contractId);

	
}
