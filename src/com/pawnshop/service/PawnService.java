package com.pawnshop.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pawnshop.po.Bag;
import com.pawnshop.po.Diamond;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.Loaner;
import com.pawnshop.po.LoanerBetween;
import com.pawnshop.po.Metal;
import com.pawnshop.po.TbContractBetween;
import com.pawnshop.po.Stone;
import com.pawnshop.po.TbProperty;
import com.pawnshop.po.Watch;

public interface PawnService {

	void insertMetal(Metal record);
	
	void insertJewellery(Jewellery jewellery);
	
	void insertBag(Bag bag);
	
	void insertDiamond(Diamond diamond);
	
	void insertStone(Stone stone);
	
	void insertWatch(Watch watch);
	
	void insertDiamond(Watch watch);
	
	void updateJewelleryState(int jid,String state);
	
	List<Jewellery> findReviewList();

	String saveJimage(MultipartFile file, Jewellery jewellery, ModelMap map)throws IOException;

	List<Loaner> findLoanerList(Loaner loaner,int page,int limit);
	
	/**
	 * 创建一个合同,返回主键
	 * @param uId 
	 * @return
	 */
	public String insertProContrct(String productType,String uId);

/**---------------------------借款人相关--------------------------------------------**/
	/**
	 * 通过合同Id获取借款人
	 * @param contractId
	 * @return
	 */
	List<Object> findLoanerByCId(LoanerBetween loanerBetween);
	
	/**
	 * 绑定loanerId和contractId
	 * @param contractId  loanerId
	 * @return 返回绑定后的借款人中间表
	 */
	List<Object> bindLoanBetween(String contractId, String loanerId,String loanerType);

	/**
	 * 新增一个loaner
	 * @param loaner
	 * @return
	 */
	int insertLoanerInfo(Loaner loaner);

	/**
	 * 查询loaner总数
	 * @return
	 */
	int countLoaners();

	/**
	 * 删除绑定的借款人
	 * @param loanerBetweenId
	 */
	void delLoanerBetween(String loanerBetweenId);

	/**
	 * 新增房产信息
	 * @param property
	 */
	int insertProperty(TbProperty property);

	/**
	 * 通过合同id和抵押类型获取绑定的房产信息
	 * @param contractId
	 * @param mortgageType
	 * @return
	 */
	List<Object> getProductByConId(String contractId,String mortgageType);

	/**
	 * 删除绑定的房产信息
	 * @param proBetId
	 */
	void delPropertyBetween(String proBetId);

	/**
	 * 统计房产总数
	 * @return
	 */
	int getCountProperty();

	/**
	 * 获取房产列表
	 * @param page
	 * @param limit
	 * @return
	 */
	List<TbProperty> getPropertyList(int page, int limit,String communityName);

	/**
	 * 绑定房产信息和合同，并返回新的列表
	 * @param propertyBetween
	 * @return
	 */
	List<Object> bindProBetween(TbContractBetween propertyBetween);

	/**
	 * 根据合同id获取审核通过后的借款人列表
	 * @param loanerBetween
	 * @return
	 */
	List<Object> getApproveLoaner(LoanerBetween loanerBetween);

	/**
	 * 通过合同id和抵押类型获取审核通过后的产品信息 
	 * @param contractId
	 * @param mortgageType
	 * @return
	 */
	List<Object> getApproveProduct(String contractId, String mortgageType);

	/**
	 * 根据id删除合同
	 * @param contractId
	 */
	void delContract(String contractId);
	
	
	
}
