package com.pawnshop.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pawnshop.common.CommonUtils;
import com.pawnshop.dao.PawnDao;
import com.pawnshop.dao.UserDao;
import com.pawnshop.dao.UtilsDao;
import com.pawnshop.po.Contract;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.TbContractIndex;
import com.pawnshop.po.TbPostLoanBetween;
import com.pawnshop.po.TbPostLoanRecord;
import com.pawnshop.po.TbReviewBetween;
import com.pawnshop.po.TbReviewRecord;
import com.pawnshop.po.User;
import com.pawnshop.service.UserService;

@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PawnDao pawnDao;
	
	@Autowired
	private UtilsDao utilsDao;
	
	@Override
	public User findUser(String username, String password) {
		// TODO Auto-generated method stub
		User user=this.userDao.findUser(username, password);
		System.out.println(user);
		return user;
	}
	
	@Override
	public User findUserByName(String username) {
		return(userDao.findUserByName(username));
	}
	
	@Override
	public List<Jewellery> findPawnList(int page, int limit) {
		return userDao.findPawnList(page, limit);
	}
	
	@Override
	public int updateInfo(User user) {
		return userDao.updateInfo(user);
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public int updateJewellery(Jewellery jewellery) {
		return userDao.updateJewellery(jewellery);
	}
	
	@Override
	@Transactional
	public String save(MultipartFile file, User user, ModelMap map) throws IOException {
		
		// 保存图片的路径，图片上传成功后，将路径保存到数据库
		String filePath = "D:\\zupload";
		// 获取原始图片的扩展名
		String originalFilename = file.getOriginalFilename();
		// 生成文件新的名字
		String newFileName = UUID.randomUUID() + originalFilename;
		// 封装上传文件位置的全路径
		File targetFile = new File(filePath, newFileName);
		file.transferTo(targetFile);
		
		// 保存到数据库
		user.setUimage(newFileName);
		userDao.save(user);
		return "redirect:/user/listImages";
	}

	@Override
	public void updatePropertyCon(Contract pContract) {
		pContract.setProductType(CommonUtils.changeProuctTypeNum(pContract.getProductType()));
		
		if(!StringUtils.isEmpty(pContract.getStatus())){
			//有值就是提交，需要有借款编号
			pContract.setContractNum(getContractNum(pContract.getProductType()));
		}
		
		userDao.updatePropertyCon(pContract);
	}

	@Override
	@Transactional
	public void submitReview(TbReviewRecord reviewRecord, String contractId) {
		//新增审核记录表数据
		reviewRecord.setUpdateTime(CommonUtils.getNowTimestamp());
		reviewRecord.setrId(CommonUtils.getDateId());
		userDao.insertReviewRecord(reviewRecord);
		
		System.out.println("【新增审核记录表】-->"+reviewRecord);
		
		//先查询是否有审核中间表
		TbReviewBetween tbReviewBetween = userDao.getReviRecordId(contractId);
		if (ObjectUtils.isEmpty(tbReviewBetween)) {
			tbReviewBetween = new TbReviewBetween();
			//空的新增并绑定
			tbReviewBetween.setContractId(contractId);
		}
		
		tbReviewBetween.setUpdateTime(CommonUtils.getNowTimestamp());
		tbReviewBetween.setrId(reviewRecord.getrId());
		
		userDao.savetReviewBet(tbReviewBetween);
		
		System.out.println("【保存审核中间表】-->"+tbReviewBetween);
	}

	@Override
	public String updateStatus(TbPostLoanRecord tbPostLoanRecord) {
		Timestamp updateTimestamp = CommonUtils.getNowTimestamp();
		
		//新增贷后管理管理更新记录表
		tbPostLoanRecord.setUpdateTime(updateTimestamp);
		tbPostLoanRecord.setPlStatus(CommonUtils.changeStatusName(tbPostLoanRecord.getUpdateReason()));
		userDao.insertPostLoanRecord(tbPostLoanRecord);
		System.out.println("【新增贷后管理更新记录表】-->"+tbPostLoanRecord);
		
		TbPostLoanBetween postLoanBetween = new TbPostLoanBetween();
		postLoanBetween.setContractId(tbPostLoanRecord.getContractId());
		postLoanBetween.setUpdateTime(updateTimestamp);
		postLoanBetween.setPlrId(tbPostLoanRecord.getPlrId());
		
		//重新绑定贷后管理中间表
		//先查询是否存在此中间表
		String plbId = userDao.getPostLoanId(tbPostLoanRecord.getContractId());
		
		postLoanBetween.setPlbId(plbId);
		userDao.savePostLoanBetween(postLoanBetween);
		System.out.println("【保存贷后管理中间表】-->"+postLoanBetween);
		
		//更新房贷表信息
		Contract Contract = new Contract();
		Contract.setContractId(tbPostLoanRecord.getContractId());
		Contract.setStatus(CommonUtils.changeStatusName(tbPostLoanRecord.getUpdateReason()));
		Contract.setUpdateTime(updateTimestamp);
		userDao.updateProConPart(Contract);
		System.out.println("【更新房贷信息表】-->"+Contract);
		
		return null;
	}

	@Override
	public Map<String, Object> submitReview(Map<String, String> map) {
		String contractId = map.get("contractId");
		String rStatus = map.get("rStatus");
		
		//修改房贷表的状态
		Contract pContract = new Contract();
		pContract.setContractId(contractId);
		pContract.setStatus(("审核通过").equals(rStatus)?"待放款":rStatus);
		
		TbReviewRecord reviewRecord = new TbReviewRecord();
		reviewRecord.setrComments(map.get("rComments"));
		reviewRecord.setuId(map.get("uId"));
		reviewRecord.setrStatus(map.get("rStatus"));
		reviewRecord.setContractId(contractId);
		
		//审核记录
		submitReview(reviewRecord,contractId);

		//绑定成功了才修改
		userDao.updateProConPart(pContract);
		
		//审核通过了就转移产品表和借款人信息表
		if (("审核通过").equals(rStatus)) {
			//备份产品表信息
			//1、先查出原有的产品信息表
			List<HashMap<String,String>> applyProducts = pawnDao.getApplyProduct(CommonUtils.getApplyParam(contractId));
			//2.添加额外的参数
			for (HashMap<String, String> applyProduct : applyProducts) {
				applyProduct.put("contractId", contractId);
				
				if ("PC".equals(contractId.substring(0, 2))) {
					userDao.insertBackupProperty(applyProduct);
				}else if("CC".equals(contractId.substring(0, 2))){
					userDao.insertBackupCar(applyProduct);
				}
				
				System.out.println("【备份了产品表】--->"+applyProduct.toString());
				logger.info("【备份了产品表】--->"+applyProduct.toString());
			}
			
			//备份借款人信息
			//1.先查出原有的借款人表
			List<HashMap<String,String>> applyLoaners = pawnDao.getApplyLoaner(contractId);
			//2.再添加额外的参数
			for (HashMap<String, String> applyLoaner : applyLoaners) {
				applyLoaner.put("contractId", contractId);
				userDao.insertBackupLoaner(applyLoaner);
				
				System.out.println("【备份了借款人表】--->"+applyLoaner.toString());
				logger.info("【备份了借款人表】--->"+applyLoaner.toString());
			}
		}
		
		System.out.println("【审核人员提交】修改Contract:"+pContract);
		
		return null;
	}


	/**
	 * 生成合同编号
	 * @param productType
	 * @return
	 */
	public String getContractNum(String productType){
		Integer contractIndex = 0; 
		TbContractIndex tbContractIndex = new TbContractIndex();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String contractDate = dateFormat.format(new Date());
		
		tbContractIndex.setProductType(productType);
		tbContractIndex.setIndexDate(contractDate);
		
		//查询编号
		TbContractIndex contractIndexGet = utilsDao.getContractIndex(tbContractIndex);
		
		tbContractIndex.setUpdateTime(CommonUtils.getNowTimestamp());
		
		if (ObjectUtils.isEmpty(contractIndexGet)) {
			contractIndex = 1;
			//新增编号计数
			tbContractIndex.setSortIndex(String.valueOf(contractIndex));
			
			utilsDao.insertContractIndex(tbContractIndex);
		}else {
			contractIndex = Integer.valueOf(contractIndexGet.getSortIndex()) + 1;
			//修改编号计数
			tbContractIndex.setSortIndex(String.valueOf(contractIndex));
			
			utilsDao.updateContractIndex(tbContractIndex);
		}
		
		String productIndex = "";
		
		switch (productType) {
		case "A0001"://房产
			productIndex = "FCDY";
			break;
		case "A0002"://汽车
			productIndex = "QCDY";
			break;
		}
		
		if (contractIndex < 10) {
			return productIndex + contractDate + "0" + contractIndex;
		}else {
			
			return productIndex + contractDate + contractIndex;
		}
	}

}
