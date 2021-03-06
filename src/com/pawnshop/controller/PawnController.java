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
	
	/*------------------------??????----------------------*/
	// ?????????????????????
		@RequestMapping(value = "/toPawn", method = RequestMethod.GET)
		public String toPawn() {
			return "pawn";
		}
	/*------------------------??????----------------------*/

	// ?????????????????????
	@RequestMapping(value = "/toEvaluation", method = RequestMethod.GET)
	public String evaluation() {
		return "evaluation";
	}

	// ????????????
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
		jewellery.setJstate("?????????");
		// ???????????????????????????????????????????????????????????????????????????
		String filePath = "D:\\zupload";
		// ??????????????????????????????
		String originalFilename = file.getOriginalFilename();
		// ????????????????????????
		String newFileName = UUID.randomUUID() + originalFilename;
		// ????????????????????????????????????
		File targetFile = new File(filePath, newFileName);
		file.transferTo(targetFile);

		jewellery.setJphoto(newFileName);
		pawnService.insertJewellery(jewellery);
	}
	
	//??????????????????
	@RequestMapping(value="/saveJimage",method = RequestMethod.POST)
	public String save(MultipartFile file, Jewellery jewellery, ModelMap map) {
		try {
			return pawnService.saveJimage(file, jewellery, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ????????????
	@RequestMapping(value = "/diamondEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void diamondEvaluation(@RequestBody Diamond diamond) {
		System.out.println("?????????????????????" + diamond);
		diamond.setDstate("?????????");
		pawnService.insertDiamond(diamond);
	}

	// ????????????
	@RequestMapping(value = "/watchEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void watchEvaluation(@RequestBody Watch watch) {
		System.out.println("?????????????????????" + watch);
		watch.setWstate("?????????");
		pawnService.insertWatch(watch);
	}
		
	// ?????????????????????
	@RequestMapping(value = "/bagEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void bagEvaluation(@RequestBody Bag bag) {
		System.out.println("?????????????????????" + bag);
		bag.setBstate("?????????");
		pawnService.insertBag(bag);
	}
		
	// ??????????????????
	@RequestMapping(value = "/stoneEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void StoneEvaluation(@RequestBody Stone stone) {
		System.out.println("?????????????????????" + stone);
		stone.setSstate("?????????");
		pawnService.insertStone(stone);
	}
		
	// ????????????
	@RequestMapping(value = "/materialEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public void materialEvaluation(@RequestBody Metal metal) {
		pawnService.insertMetal(metal);
	}
	
/*-----------------------------???????????????-----------------------------*/
	// ????????????????????????????????????
	@RequestMapping(value = "/toReview")
	public String toReview() {
		return "review";
	}
	// ?????????????????????????????????
	@RequestMapping(value = "/toReviewPawn")
	public String toReviewPawn() {
		return "reviewPawn";
	}
	// ?????????????????????
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
	
	// ????????????
	@RequestMapping(value = "/addJewellery")
	public String addJewellery() {
		return "productEdit/property/NewFile";
	}
	
	// ??????????????????????????????
	@RequestMapping(value = "/toAddProperty")
	public String toAddProperty(String contractId,
								String productType,
								String viewFlag,
								Model model,HttpSession session) {
		
		if (!StringUtils.isEmpty(viewFlag)) {
			model.addAttribute("viewFlag", viewFlag);
		}
		
		if (!StringUtils.isEmpty(contractId)) {
			//??????
			Map<String, String> propertyConMap = (Map<String, String>) adminDao.getPropertyPage(contractId);
			productType = propertyConMap.get("productType");
			
			//??????????????????????????????
			propertyConMap.put("productType", CommonUtils.changeProuctType(productType));
			
			model.addAllAttributes(propertyConMap);
		}else if (!StringUtils.isEmpty(productType) && StringUtils.isEmpty(contractId)) {
			User user = (User) session.getAttribute("user");
			
			//????????????
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
		//????????????????????????
		return pawnService.insertProContrct(uId);
	}*/
	
	/*-----------------------???????????????---------------------------*/
	
	// ???????????????????????????
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
	
	// ???????????????????????????
	@RequestMapping(value = "/toLoanerList")
	public String toLoanerList() {
		return "productEdit/loaner/list_loaner";
	}
	
	// ?????????????????????
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
	    System.out.println("?????????????????????"+data);
	    result.put("data", data);
		return result;
	}
	
	/**
	 * ????????????ID?????????????????????
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
	    System.out.println("????????????ID????????????????????????"+data);
	    result.put("data", data);
	    
		return result;
	}
	
	/**
	 * ????????????ID?????????????????? ?????????????????????
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
		System.out.println("????????????ID??????????????????????????????????????????"+data);
		result.put("data", data);
		
		return result;
	}
	
	//?????????????????????
	@RequestMapping(value = "/insertLoanerInfo",method = RequestMethod.POST,produces = {"text/html;charset=utf-8"})
	@ResponseBody
	public String insertLoanerInfo(@RequestBody Loaner loaner) {
		List<Loaner> loanerList = pawnService.findLoanerList(loaner,0,10);
		if (loanerList.size() > 0) {
			String temp = "????????????????????????:"+loaner.getCitizenID();
			return JSON.toJSONString(temp);
		}
		
		pawnService.insertLoanerInfo(loaner);
		
		return "0000";
	}
	
	//?????????????????????
	@RequestMapping(value = "/updateLoanerInfo",method = RequestMethod.POST,produces = {"text/html;charset=utf-8"})
	@ResponseBody
	public String updateLoanerInfo(@RequestBody Loaner loaner) {
		String msg = "????????????!????????????????????????!";
		
		//???????????????????????????????????????
		if (pawnDao.findLoanerPawnIng(loaner.getLoanerId()) > 0) {
			msg = "?????????????????????????????????????????????????????????!";
		}else {
			loaner.setUpdateTime(CommonUtils.getNowTimestamp());
			int updateLoanerInfo = pawnDao.updateLoanerInfo(loaner);
			
			if (1 == updateLoanerInfo) {
				msg = "0000";
			}
		}
		
		return msg;
	}
	
/*-----------------------tb_loaner_between ????????????????????????-------------------------------------*/
	/**
	 * ??????loanerId???contractId
	 * @param contractId  loanerId
	 * @return ????????????????????????????????????
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
	    System.out.println("?????????????????????????????????:"+data);
	    result.put("data", data);
		return result;
	}
	
	// ????????????????????????
	@RequestMapping(value = "/delLoanerBetween")
	@ResponseBody
	public String delLoanerBetween(String loanerBetweenId) {
		pawnService.delLoanerBetween(loanerBetweenId);
		
		return "";
	}
	
/*-----------------------tb_property ?????????????????????-------------------------------------*/
	
	//????????????????????????
	@RequestMapping(value="/toProperty")
	public String toProperty(){
		return "productEdit/property/property";
	}
	
	//??????????????????????????????
	@RequestMapping(value="/toPropertyList")
	public String toPropertyList(){
		return "productEdit/property/list_property";
	}
	
	//?????????????????? 
	@RequestMapping(value = "/insertProperty")
	@ResponseBody
	public String insertProperty(@RequestBody TbProperty property) {
		pawnService.insertProperty(property);
		
		return "0000";
	}
	
	//????????????id????????????????????????????????? 
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
		System.out.println("???????????????????????????:"+data);
		result.put("data", data);
		return result;
	}
	
	//????????????id??????????????????????????????????????????????????? 
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
		System.out.println("???????????????????????????:"+data);
		result.put("data", data);
		return result;
	}
	
	//????????????????????????????????? 
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
		
		System.out.println("?????????????????????????????????:"+data);
		result.put("data", propertyList);
		return result;
	}
/*-----------------------tb_contract_between ?????????????????????-------------------------------------*/
	
	// ???????????????????????????
	@RequestMapping(value = "/delPropertyBetween")
	@ResponseBody
	public String delPropertyBetween(String proBetId) {
		pawnService.delPropertyBetween(proBetId);
		System.out.println("??????????????????????????????????????????proBetId["+proBetId+"]??????");
		return "";
	}
	
	/**
	 * ???????????????????????????
	 * @param contractId  loanerId
	 * @return ??????????????????????????????
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
		System.out.println("?????????????????????????????????:"+data);
		result.put("data", data);
		return result;
	}
	
	/**
	 * ????????????????????????
	 * @param contractId
	 * @return
	 */
	@RequestMapping(value = "/toReviewRecord",method = RequestMethod.GET)
	public String toReviewRecord(@RequestParam String contractId,Model model){
		model.addAttribute("contractId", contractId);
		return "productEdit/review/reviewRecord";
	}
	
	/**
	 * ????????????id??????????????????
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
		System.out.println("????????????????????????:"+reviewRecordList);
		result.put("data", reviewRecordList);
		return result;
	}
	
	/**
	 * ????????????id????????????????????????
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
		
		System.out.println("????????????????????????????????????:"+postLoanRecord);
		
		return result;
	}
	
	/**
	 * ????????????
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
