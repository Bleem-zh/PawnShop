package com.pawnshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pawnshop.common.CommonUtils;
import com.pawnshop.dao.AdminDao;
import com.pawnshop.dao.PawnDao;
import com.pawnshop.po.Bag;
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
import com.pawnshop.po.User;
import com.pawnshop.po.Watch;
import com.pawnshop.service.PawnService;
import com.pawnshop.service.impl.PawnServiceImpl;

@Controller
@RequestMapping(value = "/pawn")
public class PawnController {
	@Autowired
	private PawnService pawnService;
	
	@Autowired
	private PawnDao pawnDao;
	
	@Autowired
	private AdminDao adminDao;
	
	/*------------------------典当----------------------*/
	// 跳转到典当页面
		@RequestMapping(value = "/toPawn", method = RequestMethod.GET)
		public String toPawn() {
			return "pawn";
		}
	/*------------------------估当----------------------*/

	// 跳转到估当页面
	@RequestMapping(value = "/toEvaluation", method = RequestMethod.GET)
	public String evaluation() {
		return "evaluation";
	}

	// 珠宝估当
	@RequestMapping(value = "/jewelleryEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void jewelleryEvaluation(String jbrand,String jmaterial,String jinmaterial,Double jusage,
				String jattachment,Double jpremoney,Date jbuytime,@RequestParam( value="jimg",required=false)MultipartFile file) throws IOException {
		Jewellery jewellery = new Jewellery();
		jewellery.setJbrand(jbrand);
		jewellery.setJmaterial(jmaterial);
		jewellery.setJinmaterial(jinmaterial);
		jewellery.setJusage(jusage);
		jewellery.setJattachment(jattachment);
		jewellery.setJpremoney(jpremoney);
		jewellery.setJbuytime(jbuytime);
		jewellery.setJstate("估当中");
		// 保存图片的路径，图片上传成功后，将路径保存到数据库
		String filePath = "D:\\zupload";
		// 获取原始图片的扩展名
		String originalFilename = file.getOriginalFilename();
		// 生成文件新的名字
		String newFileName = UUID.randomUUID() + originalFilename;
		// 封装上传文件位置的全路径
		File targetFile = new File(filePath, newFileName);
		file.transferTo(targetFile);

		jewellery.setJphoto(newFileName);
		pawnService.insertJewellery(jewellery);
	}
	
	//保存珠宝图片
	@RequestMapping(value="/saveJimage",method = RequestMethod.POST)
	public String save(MultipartFile file, Jewellery jewellery, ModelMap map) {
		try {
			return pawnService.saveJimage(file, jewellery, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 钻石估当
	@RequestMapping(value = "/diamondEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void diamondEvaluation(@RequestBody Diamond diamond) {
		System.out.println("要估当的钻石：" + diamond);
		diamond.setDstate("估当中");
		pawnService.insertDiamond(diamond);
	}

	// 手表估当
	@RequestMapping(value = "/watchEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void watchEvaluation(@RequestBody Watch watch) {
		System.out.println("要估当的手表：" + watch);
		watch.setWstate("估当中");
		pawnService.insertWatch(watch);
	}
		
	// 奢侈品箱包估当
	@RequestMapping(value = "/bagEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void bagEvaluation(@RequestBody Bag bag) {
		System.out.println("要估当的箱包：" + bag);
		bag.setBstate("估当中");
		pawnService.insertBag(bag);
	}
		
	// 翡翠玉石估当
	@RequestMapping(value = "/stoneEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void StoneEvaluation(@RequestBody Stone stone) {
		System.out.println("要估当的石头：" + stone);
		stone.setSstate("估当中");
		pawnService.insertStone(stone);
	}
		
	// 金属估当
	@RequestMapping(value = "/materialEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void materialEvaluation(@RequestBody Metal metal) {
		pawnService.insertMetal(metal);
	}
	
/*-----------------------------管理员操作-----------------------------*/
	// 管理员页面跳转到审核页面
	@RequestMapping(value = "/toReview")
	public String toReview() {
		return "review";
	}
	// 审核页面跳转到审核详情
	@RequestMapping(value = "/toReviewPawn")
	public String toReviewPawn() {
		return "reviewPawn";
	}
	// 得到待审核列表
	@RequestMapping(value = "/getReviewList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getReviewList(@RequestParam String page,@RequestParam int limit) {
		Map<String, Object> result = new HashMap<String, Object>();
	    result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",50);
	    Object data=JSON.toJSON(pawnService.findReviewList());
	    System.out.println(data);
	    result.put("data", data);
		return result;
	}
	
	// 测试页面
	@RequestMapping(value = "/addJewellery")
	public String addJewellery() {
		return "productEdit/property/NewFile";
	}
	
	// 跳转编辑产品合同页面
	@RequestMapping(value = "/toAddProperty")
	public String toAddProperty(String contractId,
								String productType,
								String viewFlag,
								Model model,HttpSession session) {
		
		if (!StringUtils.isEmpty(viewFlag)) {
			model.addAttribute("viewFlag", viewFlag);
		}
		
		if (!StringUtils.isEmpty(contractId)) {
			//跳转
			Map<String, String> propertyConMap = (Map<String, String>) adminDao.getPropertyPage(contractId);
			productType = propertyConMap.get("productType");
			
			//典当物产品类型的转换
			propertyConMap.put("productType", CommonUtils.changeProuctType(productType));
			
			model.addAllAttributes(propertyConMap);
		}else if (!StringUtils.isEmpty(productType) && StringUtils.isEmpty(contractId)) {
			User user = (User) session.getAttribute("user");
			
			//新增合同
			contractId = pawnService.insertProContrct(productType,String.valueOf(user.getUid()));
			model.addAttribute("contractId", contractId);
			model.addAttribute("productType", CommonUtils.changeProuctType(productType));
		}
		
		if ("A0001".equals(productType)) {
			return "productEdit/property/add_property";
		}else if ("A0002".equals(productType)) {
			return "productEdit/car/carContract";
		}else {
			return "";
		}
	}
	
/*	@RequestMapping(value = "/addProperty",method=RequestMethod.POST)
	@ResponseBody
	public String addContract(String uId){
		//创建一个房贷合同
		return pawnService.insertProContrct(uId);
	}*/
	
	/*-----------------------借款人操作---------------------------*/
	
	// 跳转增加借款人页面
	@RequestMapping(value = "/toLoaner",method = RequestMethod.GET)
	public String toLoaner(String loanerId,Model model) {
		if (!StringUtils.isEmpty(loanerId)) {
			Loaner loaner = new Loaner();
			loaner.setLoanerId(loanerId);
			
			List<Loaner> loanerList = pawnDao.findLoanerList(loaner,0,1);
			model.addAttribute("loaner", loanerList.get(0));
			model.addAttribute("editFlag", "1");
		}
		
		return "productEdit/loaner/add_loaner";
	}
	
	// 跳转借款人列表页面
	@RequestMapping(value = "/toLoanerList")
	public String toLoanerList() {
		return "productEdit/loaner/list_loaner";
	}
	
	// 获取借款人列表
	@RequestMapping(value = "/getLonerList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getLonerList(@RequestParam int page,@RequestParam int limit,
				@RequestParam(required=false) String citizenID) {
		Map<String, Object> result = new HashMap<String, Object>();
		Loaner loaner = new Loaner();
		
		if (!StringUtils.isEmpty(citizenID)) {
			loaner.setCitizenId(citizenID);
		}
		
		List<Loaner> loanerList = pawnService.findLoanerList(loaner,(page-1)*limit,limit);
	    result.put("code", 0);
	    result.put("msg", "");
	    
	    result.put("count",pawnService.countLoaners());
	    
	    Object data=JSON.toJSON(loanerList);
	    System.out.println("获取借款人表："+data);
	    result.put("data", data);
		return result;
	}
	
	/**
	 * 根据合同ID获取借款人列表
	 * @param ContractId
	 * @return
	 */
	@RequestMapping(value = "/getLoanerByConId",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getLoanerByConId(@RequestParam int page,@RequestParam int limit,
							@RequestParam String contractId,@RequestParam String loanerType) {
		int count = 0;
		List<Object> list = new ArrayList<>();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		if (StringUtils.isEmpty(contractId)) {
			count = 0;
		}else {
			LoanerBetween loanerBetween = new LoanerBetween();
			loanerBetween.setContractId(contractId);
			loanerBetween.setLoanerType(loanerType);
			
			list = pawnService.findLoanerByCId(loanerBetween);
			count = list.size();
		}
		
	    result.put("count",count);
	    Object data=JSON.toJSON(list);
	    System.out.println("根据合同ID获取借款人列表："+data);
	    result.put("data", data);
	    
		return result;
	}
	
	/**
	 * 根据合同ID获取审核通过 后的借款人列表
	 * @param ContractId
	 * @return
	 */
	@RequestMapping(value = "/getApproveLoaner",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getApproveLoaner(@RequestParam int page,@RequestParam int limit,
			@RequestParam String contractId,@RequestParam String loanerType) {
		int count = 0;
		List<Object> list = new ArrayList<>();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		if (StringUtils.isEmpty(contractId)) {
			count = 0;
		}else {
			LoanerBetween loanerBetween = new LoanerBetween();
			loanerBetween.setContractId(contractId);
			loanerBetween.setLoanerType(loanerType);
			
			list = pawnService.getApproveLoaner(loanerBetween);
			count = list.size();
		}
		
		result.put("count",count);
		Object data=JSON.toJSON(list);
		System.out.println("根据合同ID获取审核通过后的借款人列表："+data);
		result.put("data", data);
		
		return result;
	}
	
	//新增借款人信息
	@RequestMapping(value = "/insertLoanerInfo",method = RequestMethod.POST,produces = {"text/html;charset=utf-8"})
	@ResponseBody
	public String insertLoanerInfo(@RequestBody Loaner loaner) {
		List<Loaner> loanerList = pawnService.findLoanerList(loaner,0,10);
		if (loanerList.size() > 0) {
			String temp = "此身份证号已存在:"+loaner.getCitizenID();
			return JSON.toJSONString(temp);
		}
		
		pawnService.insertLoanerInfo(loaner);
		
		return "0000";
	}
	
	//修改借款人信息
	@RequestMapping(value = "/updateLoanerInfo",method = RequestMethod.POST,produces = {"text/html;charset=utf-8"})
	@ResponseBody
	public String updateLoanerInfo(@RequestBody Loaner loaner) {
		String msg = "修改失败!请联系系统管理员!";
		
		//查询是否有在途的借款人申请
		if (pawnDao.findLoanerPawnIng(loaner.getLoanerId()) > 0) {
			msg = "该借款人有在途的借款申请，不可修改信息!";
		}else {
			loaner.setUpdateTime(CommonUtils.getNowTimestamp());
			int updateLoanerInfo = pawnDao.updateLoanerInfo(loaner);
			
			if (1 == updateLoanerInfo) {
				msg = "0000";
			}
		}
		
		return msg;
	}
	
/*-----------------------tb_loaner_between 借款人中间表操作-------------------------------------*/
	/**
	 * 绑定loanerId和contractId
	 * @param contractId  loanerId
	 * @return 返回绑定后的借款人中间表
	 */
	@RequestMapping(value = "/bindLoanBetween",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> bindLoanBetween(@RequestParam int page,
												@RequestParam int limit,
												@RequestParam String contractId,
												@RequestParam String loanerId,
												@RequestParam String loanerType) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Object> loanerList = pawnService.bindLoanBetween(contractId,loanerId,loanerType);
	    result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",loanerList.size());
	    Object data=JSON.toJSON(loanerList);
	    System.out.println("查询绑定的借款人中间表:"+data);
	    result.put("data", data);
		return result;
	}
	
	// 删除绑定的借款人
	@RequestMapping(value = "/delLoanerBetween")
	@ResponseBody
	public String delLoanerBetween(String loanerBetweenId) {
		pawnService.delLoanerBetween(loanerBetweenId);
		
		return "";
	}
	
/*-----------------------tb_property 房产信息表操作-------------------------------------*/
	
	//跳转房产信息界面
	@RequestMapping(value="/toProperty")
	public String toProperty(){
		return "productEdit/property/property";
	}
	
	//获取房产信息列表界面
	@RequestMapping(value="/toPropertyList")
	public String toPropertyList(){
		return "productEdit/property/list_property";
	}
	
	//新增房产信息 
	@RequestMapping(value = "/insertProperty")
	@ResponseBody
	public String insertProperty(@RequestBody TbProperty property) {
		pawnService.insertProperty(property);
		
		return "0000";
	}
	
	//通过合同id和抵押类型获取房产信息 
	@RequestMapping(value = "/getPropertyByConId")
	@ResponseBody
	public Map<String, Object> getPropertyByConId(@RequestParam int page,@RequestParam int limit,
			@RequestParam String contractId,@RequestParam String mortgageType) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Object> loanerList = pawnService.getProductByConId(contractId,mortgageType);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",loanerList.size());
		Object data=JSON.toJSON(loanerList);
		System.out.println("【查询绑定的房产】:"+data);
		result.put("data", data);
		return result;
	}
	
	//通过合同id和抵押类型获取审核通过后的产品信息 
	@RequestMapping(value = "/getApproveProduct")
	@ResponseBody
	public Map<String, Object> getApproveProduct(@RequestParam int page,@RequestParam int limit,
			@RequestParam String contractId,@RequestParam String mortgageType) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Object> loanerList = pawnService.getApproveProduct(contractId,mortgageType);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",loanerList.size());
		Object data=JSON.toJSON(loanerList);
		System.out.println("【查询绑定的房产】:"+data);
		result.put("data", data);
		return result;
	}
	
	//获取所有的房产信息列表 
	@RequestMapping(value = "/getPropertyList")
	@ResponseBody
	public Map<String, Object> getPropertyList(@RequestParam int page,@RequestParam int limit,String communityName) {
		Map<String, Object> result = new HashMap<String, Object>();
		page = (page-1)*limit;
		
		List<TbProperty> propertyList = pawnService.getPropertyList(page,limit,communityName);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",pawnService.getCountProperty());
		Object data=JSON.toJSON(propertyList);
		
		Object json = JSONObject.toJSON(propertyList);
		
		System.out.println("【分页查询所有的房产】:"+data);
		result.put("data", propertyList);
		return result;
	}
/*-----------------------tb_contract_between 房产中间表操作-------------------------------------*/
	
	// 删除绑定的房产信息
	@RequestMapping(value = "/delPropertyBetween")
	@ResponseBody
	public String delPropertyBetween(String proBetId) {
		pawnService.delPropertyBetween(proBetId);
		System.out.println("【删除绑定房产成功】，删除了proBetId["+proBetId+"]记录");
		return "";
	}
	
	/**
	 * 绑定合同和房产信息
	 * @param contractId  loanerId
	 * @return 返回绑定后的房产信息
	 */
	@RequestMapping(value = "/bindProBetween",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> bindProBetween(@RequestBody TbContractBetween propertyBetween ) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Object> loanerList = pawnService.bindProBetween(propertyBetween);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",loanerList.size());
		Object data=JSON.toJSON(loanerList);
		System.out.println("【查询绑定的房产信息】:"+data);
		result.put("data", data);
		return result;
	}
	
	/**
	 * 跳转审核记录界面
	 * @param contractId
	 * @return
	 */
	@RequestMapping(value = "/toReviewRecord",method = RequestMethod.GET)
	public String toReviewRecord(@RequestParam String contractId,Model model){
		model.addAttribute("contractId", contractId);
		return "productEdit/review/reviewRecord";
	}
	
	/**
	 * 根据合同id获取审核记录
	 * @param propertyBetween
	 * @return
	 */
	@RequestMapping(value = "/getReviewRecordList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReviewRecordList(@RequestParam String contractId ) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<TbReviewRecord> reviewRecordList = pawnDao.getReviewRecordList(contractId);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",reviewRecordList.size());
		Object data=JSON.toJSON(reviewRecordList);
		System.out.println("【查询审核记录】:"+reviewRecordList);
		result.put("data", reviewRecordList);
		return result;
	}
	
	/**
	 * 根据合同id获取贷后更新记录
	 * @param propertyBetween
	 * @return
	 */
	@RequestMapping(value = "/getPostLoanRecord",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPostLoanRecord(@RequestParam String contractId,
							@RequestParam int page,@RequestParam int limit) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		
		List<TbPostLoanRecord> postLoanRecord = pawnDao.getPostLoanRecord(contractId,(page-1)*limit,limit);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",pawnDao.countPostLoanRecord(contractId));
		Object data=JSON.toJSON(postLoanRecord);
		result.put("data", postLoanRecord);
		
		System.out.println("【查询贷后管理更新记录】:"+postLoanRecord);
		
		return result;
	}
	
	/**
	 * 删除合同
	 * @param contractId
	 * @return
	 */
	@PostMapping("/delContract")
	@ResponseBody
	public Map<String, Object> delContract(@RequestParam String contractId) {
		Map<String, Object> result = new HashMap<>();
		
		pawnService.delContract(contractId);
		
		return result;
	}
	
}
