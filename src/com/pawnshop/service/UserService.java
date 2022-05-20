package com.pawnshop.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.pawnshop.po.Jewellery;
import com.pawnshop.po.Contract;
import com.pawnshop.po.TbPostLoanRecord;
import com.pawnshop.po.TbReviewRecord;
import com.pawnshop.po.User;

public interface UserService {
	public User findUser(String username,String password);
	
	public User findUserByName(String username);
	
	public List<Jewellery> findPawnList(int page,int limit);
	
	public int updateInfo(User user);
	
	public int updateJewellery(Jewellery jewellery);
	
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
	public String save(MultipartFile file, User user, ModelMap map)throws IOException;

	/**
	 * 修改房贷合同信息 
	 * @param pContract
	 */
	public void updatePropertyCon(Contract pContract);

	/**
	 * 审核人员提交后的更新
	 * @param reviewRecord
	 * @param contractId
	 */
	public void submitReview(TbReviewRecord reviewRecord, String contractId);

	/**
	 * 贷后状态更新
	 * @return
	 */
	public String updateStatus(TbPostLoanRecord tbPostLoanRecord);

	/**
	 * 审核提交
	 * @param map
	 * @return
	 */
	public Map<String, Object> submitReview(Map<String, String> map);
}
