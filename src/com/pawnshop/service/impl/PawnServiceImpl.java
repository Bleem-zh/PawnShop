package com.pawnshop.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.pawnshop.common.CommonUtils;
import com.pawnshop.dao.PawnDao;
import com.pawnshop.po.Bag;
import com.pawnshop.po.Contract;
import com.pawnshop.po.Diamond;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.Loaner;
import com.pawnshop.po.LoanerBetween;
import com.pawnshop.po.Metal;
import com.pawnshop.po.TbContractBetween;
import com.pawnshop.po.Stone;
import com.pawnshop.po.TbProperty;
import com.pawnshop.po.Watch;
import com.pawnshop.service.PawnService;

@Service("pawnService")
@Transactional
public class PawnServiceImpl implements PawnService{

	@Autowired
	private PawnDao pawnDao;
	
	@Override
	public void insertMetal(Metal metal){
		System.out.println("添加金属当品成功，修改了"+pawnDao.insertMetal(metal)+"行记录");
	}

	@Override
	public void insertJewellery(Jewellery jewellery) {
		System.out.println("添加珠宝当品成功，修改了"+pawnDao.insertJewellery(jewellery)+"行记录");
		
	}

	@Override
	public void insertBag(Bag bag) {
		System.out.println("添加名贵包包当品成功，修改了"+pawnDao.insertBag(bag)+"行记录");
	}

	@Override
	public void insertDiamond(Diamond diamond) {
		System.out.println("添加钻石当品成功，修改了"+pawnDao.insertDiamond(diamond)+"行记录");
	}

	@Override
	public void insertStone(Stone stone) {
		System.out.println("添加翡翠当品成功，修改了"+pawnDao.insertStone(stone)+"行记录");
	}

	@Override
	public void insertWatch(Watch watch) {
		System.out.println("添加手表当品成功，修改了"+pawnDao.insertWatch(watch)+"行记录");
	}

	@Override
	public void updateJewelleryState(int jid, String state) {
		pawnDao.updateJewelleryState(jid, state);
	}

	@Override
	public List<Jewellery> findReviewList() {
		return pawnDao.findReviewList();
	}

	@Override
	public void insertDiamond(Watch watch) {
	}

	@Override
	public String saveJimage(MultipartFile file, Jewellery jewellery,               //这个方法的内容写在controller里了，所以这里没有用
			ModelMap map) throws IOException {
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
				jewellery.setJphoto(newFileName);
				pawnDao.saveJphoto(jewellery);
		return null;
	}

	@Override
	public List<Loaner> findLoanerList(Loaner loaner,int page,int limit) {
		return pawnDao.findLoanerList(loaner,page,limit);
	}
	
	/**
	 * 创建一个房贷合同,返回主键
	 * @return
	 */
	@Override
	public String insertProContrct(String productType,String uId){
		String contractId = CommonUtils.getContractUUID(productType);
		
		Contract contract = new Contract();
		contract.setContractId(contractId);
		contract.setuId(uId);
		contract.setUpdateTime(CommonUtils.getNowTimestamp());
		contract.setProductType(productType);
		
		pawnDao.insertContract(contract);
		System.out.println("【新增了一个房贷合同】，contractId："+contractId+",uId:"+uId);
		
		return contractId;
	}

	@Override
	public List<Object> findLoanerByCId(LoanerBetween loanerBetween) {
		return pawnDao.findLoanerByCId(loanerBetween);
	}

	@Override
	public List<Object> bindLoanBetween(String contractId, String loanerId,String loanerType) {
		LoanerBetween loanerBetween = new LoanerBetween();
		String loanerBetweenId = CommonUtils.getDateId();
		
		loanerBetween.setLoanerBetweenId(loanerBetweenId);
		loanerBetween.setContractId(contractId);
		loanerBetween.setLoanerId(loanerId);
		loanerBetween.setLoanerType(loanerType);
		
		//先绑定
		pawnDao.insertLoanBetween(loanerBetween);
		System.out.println("新增借款人中间表-》tb_loaner_between:"+loanerBetween);
		
		//用contractId 和 loanerType查找相应的借款人
		List<Object> conLoaner = pawnDao.findContratLoaner(contractId,loanerType);
		
		return conLoaner;
	}

	@Override
	public int insertLoanerInfo(Loaner loaner) {
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		
		loaner.setLoanerId(CommonUtils.getDateId());
		loaner.setUpdateTime(updateTime);
		
		System.out.println("【新增的借款人信息】为："+loaner);
		
		return pawnDao.insertLoaner(loaner);
	}

	@Override
	public int countLoaners() {
		return pawnDao.countLoaners();
	}

	@Override
	public void delLoanerBetween(String loanerBetweenId) {
		pawnDao.delLoanerBetween(loanerBetweenId);
		System.out.println("【删除绑定借款人成功】，删除了loanerBetweenId["+loanerBetweenId+"]记录");
	}

	@Override
	public int insertProperty(TbProperty property) {
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());

		property.setpropertyId(CommonUtils.getDateId());
		property.setUpdateTime(updateTime);
		
		System.out.println("【新增房产信息】:"+property);
		return pawnDao.insertProperty(property);
	}

	@Override
	public List<Object> getProductByConId(String contractId,String mortgageType) {
		HashMap<String,String> applyParam = CommonUtils.getApplyParam(contractId);
		applyParam.put("mortgageType", mortgageType);
		
		return pawnDao.getProductByConId(applyParam);
	}

	@Override
	public void delPropertyBetween(String proBetId) {
		pawnDao.delPropertyBetween(proBetId);
	}

	@Override
	public int getCountProperty() {
		int countProperty = pawnDao.getCountProperty();
		System.out.println("【统计房产总数】，共"+countProperty+"条");
		
		return countProperty;
	}

	@Override
	public List<TbProperty> getPropertyList(int page, int limit,String communityName) {
		
		return pawnDao.getPropertyList(page,limit,communityName);
	}

	@Override
	public List<Object> bindProBetween(TbContractBetween tbContractBetween) {
		tbContractBetween.setProBetId(CommonUtils.getDateId());
		tbContractBetween.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		//绑定
		pawnDao.inserProBetween(tbContractBetween);
		System.out.println("【新增了tb_contract_between】"+tbContractBetween);
		
		//查询最新的
		return getProductByConId(tbContractBetween.getContractId(),tbContractBetween.getMortgageType());

	}

	@Override
	public List<Object> getApproveLoaner(LoanerBetween loanerBetween) {
		return pawnDao.getApproveLoaner(loanerBetween);
	}

	@Override
	public List<Object> getApproveProduct(String contractId,
			String mortgageType) {
		//获取产品所在的备份表信息
		HashMap<String,String> backupParam = CommonUtils.getBackupParam(contractId);
		
		return pawnDao.getApproveProduct(backupParam.get("backupTb"),
				backupParam.get("primaryKeyName"),
				contractId,
				mortgageType);
	}

	@Override
	public void delContract(String contractId) {
		pawnDao.delContract(contractId);
	}

}
