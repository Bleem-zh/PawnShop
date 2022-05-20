package com.pawnshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pawnshop.po.Jewellery;
import com.pawnshop.po.Contract;
import com.pawnshop.po.TbLoginRecord;
import com.pawnshop.po.TbPostLoanBetween;
import com.pawnshop.po.TbPostLoanRecord;
import com.pawnshop.po.TbReviewBetween;
import com.pawnshop.po.TbReviewRecord;
import com.pawnshop.po.User;

public interface UserDao {
	public User findUser(@Param("username") String username,
						@Param("password") String password);
	
	public User findUserByName(String username);
	
	public int updateInfo(@Param("user") User user);
	
	public List<Jewellery> findPawnList(@Param("page")int page,
										@Param("limit")int limit);
	
	public int updateJewellery(@Param("jewellery") Jewellery jewellery);
	
	/**
	 * 查询所有的图片
	 * @return
	 */
	List<User> list();
	
	/**
	 * 上传一张图片
	 * @param product
	 * @return
	 */
	Integer save(User user);

	public void updatePropertyCon(Contract pContract);
	
	/**
	 * 房贷表信息局部修改
	 * @param pContract
	 */
	public int updateProConPart(Contract pContract);

	/**
	 * 根据合同id获取审核记录表的id
	 * @param contractId
	 * @return
	 */
	public TbReviewBetween getReviRecordId(String contractId);

	/**
	 * 新增审核记录表
	 * @param reviewRecord
	 */
	public void insertReviewRecord(TbReviewRecord reviewRecord);

	/**
	 * 保存中间表信息
	 * @param tbReviewBetween
	 */
	public void savetReviewBet(TbReviewBetween tbReviewBetween);

	/**
	 * 新增贷后管理更新记录
	 * @param tbPostLoanRecord
	 */
	public void insertPostLoanRecord(TbPostLoanRecord tbPostLoanRecord);

	/**
	 * 用合同id查询贷后管理中间表
	 * @param contractId
	 * @return
	 */
	public String getPostLoanId(String contractId);

	/**
	 * 保存贷后管理中间表
	 * @param postLoanBetween
	 */
	public void savePostLoanBetween(TbPostLoanBetween postLoanBetween);
	
	/**
	 * 房产表的备份
	 * @param backupTb
	 * @param sourceTb
	 * @param primaryKeyName
	 * @param contractId
	 */
	/*public void insertBackupProduct(String backupTb,
				String sourceTb,String primaryKeyName,String contractId);*/
	public void insertBackupProperty(@Param("paramMap")HashMap<String, String> paramMap);
	
	/**
	 * 汽车表的备份
	 * @param paramMap
	 */
	public void insertBackupCar(@Param("paramMap")HashMap<String, String> paramMap);
	
	/**
	 * 借款人表的备份
	 */
	public void insertBackupLoaner(@Param("paramMap")HashMap<String, String> paramMap);

}
