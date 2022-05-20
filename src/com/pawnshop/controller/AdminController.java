package com.pawnshop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawnshop.dao.AdminDao;
import com.pawnshop.dao.PawnDao;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.User;
import com.pawnshop.po.Contract;
import com.pawnshop.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private PawnDao pawnDao;
	
	//跳转到待审核典当物列表页面
	@RequestMapping(value = "/toReview")
	public String toReview() {
		return "admin/review";
	}
	
	//跳转到审核页面
	@RequestMapping(value = "/toReviewPawn")
	public String toReviewPawn() {
		return "admin/reviewPawn";
	}
	
	//跳转到贷后管理列表界面
	@RequestMapping(value = "/toPostLoan",method = RequestMethod.GET)
	public String toPostLoan(@RequestParam("contractId") String contractId,String viewFlag, Model model) {
		System.out.println(contractId+"----->"+viewFlag);
		
		if ("1".equals(viewFlag)) {
			model.addAttribute("viewFlag", viewFlag);
		}else{
			model.addAttribute("viewFlag", "0");
		}
		
		model.addAttribute("contractId", contractId);
		
		String destPage = "";
		switch (contractId.substring(0, 2)) {
		case "PC":
			destPage = "postLoan/postLoanProperty";
			break;

		case "CC":
			destPage = "postLoan/postLoanCar";
			
			break;
		}
		
		return destPage;
	}
	
	//跳转到贷后管理状态更新界面
	@RequestMapping(value = "/toPostLoanList")
	public String toPostLoanList() {
		return "postLoan/postLoanList";
	}
	
	//跳转到贷后管理历史借款列表界面
	@GetMapping("/toHistoryPostLoanList")
	public String toHistoryPostLoanList() {
		return "postLoan/HistoryPostLoanList";
	}
	
	//获取待审核的房产列表
	@RequestMapping(value = "/getReviewJList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getReviewJList(@RequestParam int page,
											 @RequestParam int limit,
											 @RequestParam String uId,
											 @RequestParam String urole,
											 String balance) throws JsonProcessingException {
		System.out.println("page:"+page+"limit"+limit);

		Map<String, Object> result = new HashMap<String, Object>();
		//0是管理员
		if ("0".equals(urole)) {
			uId = "";
		}
		
		//查询待审核总数
		int count = adminDao.countPropertyReview(uId);
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",count);
		
		//List<Object> findReviewJList = adminService.findReviewJList((page-1)*limit, limit,uId,balance);
		
		Object data=JSON.toJSON(adminService.findReviewJList((page-1)*limit, limit,uId,balance));
		System.out.println("【查询待审核的典当列表】"+data);
		result.put("data", data);
		return result;
	}
	
	//跳转到用户管理页面
	@RequestMapping(value = "/toUManagement")
	public String toUManagement() {
		return "admin/umanagement";
	}
	
	//跳转到用户详情页面
	@RequestMapping(value = "/toUDetail")
	public String toUDetail() {
		return "admin/udetail";
	}
	
	//获取用户列表
	@RequestMapping(value = "/getUList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getUList(@RequestParam int page,@RequestParam int limit) throws JsonProcessingException {
		System.out.println("page:"+page+"limit"+limit);
		Map<String, Object> result = new HashMap<String, Object>();
	    result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",adminDao.countUser());
	    Object data=JSON.toJSON(adminService.findUList((page-1)*limit, limit));
	    System.out.println(data);
	    result.put("data", data);
		return result;
	}
	
	//根据id获取用户信息
	@RequestMapping(value = "/getUInfo",method = RequestMethod.GET)
	@ResponseBody
	public User getUInfo(@RequestParam int uid){
		System.out.println("根据uid="+uid+"获取到的用户信息为"+adminDao.findUInfo(uid));
		return adminDao.findUInfo(uid);
	}
	
	//跳转到编辑用户信息页面
	@RequestMapping(value = "/toUAlter")
	public String toUAlter() {
		return "admin/ualter";
	}
	
	//修改用户信息
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	@ResponseBody
	public void updateUser(@RequestBody User user){
		System.out.println("要修改id为"+user.getUid()+"的用户信息:"+user);
		adminDao.updateUser(user);
	}
	
	//跳转房产管理
	@RequestMapping(value = "/toPropertyManagement")
	public String toProperty() {
		return "productEdit/property/manage_property";
	}
	
	
	//获取产品的管理列表
	@RequestMapping(value = "/getProList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getProList(@RequestParam int page,
										 @RequestParam int limit,
										 @RequestParam String productType,
										 String balance,
										 HttpSession session) throws JsonProcessingException {
		User user = (User) session.getAttribute("user");
		String uId = String.valueOf(user.getUid());
		
		//管理员
		if ("0".equals(String.valueOf(user.getUrole()))) {
			uId="";
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		
		List<Contract> findProList = adminService.findAProList((page-1)*limit, limit,uId,balance,productType);
		
		System.out.println("【获取房贷列表】-->"+findProList);
		
		result.put("count",pawnDao.findAProCount(uId,productType));
		Object data=JSON.toJSON(findProList);
		//System.out.println(data);
		result.put("data", data);
		return result;
	}
	
	/**
	 * 通过合同Id获取提交后的房产信息界面
	 * @param contractId
	 * @return
	 */
	@RequestMapping(value="/getPropertyPage",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPropertyPage(@RequestParam String contractId){
		Map<String, Object> result = new HashMap<String, Object>();
		
		Object object = adminDao.getPropertyPage(contractId);
		
		System.out.println("【通过合同Id获取提交后的房产信息界面】-->"+"["+contractId+"]:"+object);
		result.put("data", object);
		return result;
	}
	
	/**
	 * 获取贷后管理列表界面数据
	 * @param uId
	 * @return
	 */
	@RequestMapping(value="/getPostLoanList",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPostLoanList(@RequestParam int page,
											 @RequestParam int limit,
											 @RequestParam String uId,
											 @RequestParam String urole,
											 String contractId ){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		page = (page-1)*limit;
		
		//管理员
		if ("0".equals(urole)) {
			uId="";
		}
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",adminDao.countPostLoanList(uId));
		
		List<Object> gePostLoanList = adminDao.gePostLoanList(page, limit, uId, contractId);
		
		System.out.println("【获取贷后管理列表界面数据】-->"+gePostLoanList);
		result.put("data", gePostLoanList);
		return result;
	}
	
	/**
	 * 获取历史借款列表界面数据
	 * @param uId
	 * @return
	 */
	@RequestMapping(value="/getHistoryPostLoanList",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getHistoryPostLoanList(@RequestParam int page,
			@RequestParam int limit,
			@RequestParam String uId,
			@RequestParam String urole,
			String contractId ){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		page = (page-1)*limit;
		
		//管理员
		if ("0".equals(urole)) {
			uId="";
		}
		
		result.put("code", 0);
		result.put("msg", "");
		result.put("count",adminDao.countHistoryPostLoanList(uId));
		
		List<Object> geHisPostLoanList = adminDao.geHistoryPostLoanList(page, limit, uId, contractId);
		
		System.out.println("【获取贷后管理列表界面数据】-->"+geHisPostLoanList);
		result.put("data", geHisPostLoanList);
		return result;
	}
	
}
