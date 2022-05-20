package com.pawnshop.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawnshop.dao.AdminDao;
import com.pawnshop.dao.PawnDao;
import com.pawnshop.dao.UserDao;
import com.pawnshop.po.Jewellery;
import com.pawnshop.po.Contract;
import com.pawnshop.po.TbPostLoanRecord;
import com.pawnshop.po.User;
import com.pawnshop.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private PawnDao pawnDao;
	
	//修改用户资料
	@RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
	@ResponseBody
	public User login(@RequestBody User user) {
		System.out.println("修改用户名为："+user.getUsername()+"的信息:"+user);
		userService.updateInfo(user);
		return user;
	}
	//获取用户典当物列表
	@RequestMapping(value = "/getPawnData",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getPawnData(@RequestParam int page,@RequestParam int limit) throws JsonProcessingException {
		System.out.println("page:"+page+"limit"+limit);
		Map<String, Object> result = new HashMap<String, Object>();
	    result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",50);
	    Object data=JSON.toJSON(userService.findPawnList(page, limit));
	    System.out.println(data);
	    result.put("data", data);
		return result;
	}
	
	//修改珠宝信息
	@RequestMapping(value = "/updateJewellery",method = RequestMethod.POST)
	@ResponseBody
	public void updateJewellery(@RequestBody Jewellery jewellery) {
		System.out.println("要修改的珠宝信息为："+jewellery);
		userService.updateJewellery(jewellery);
	}
	
	//跳转到个人中心
	@RequestMapping(value = "/toUserView")
	public String toUserView(HttpServletRequest request) {
		return "user/userView";
	}
	
	//返回首页
	@RequestMapping(value = "/backToMain")
	public String backToMain() {
		return "mainlogined";
	}
	
	//返回登录页面
	@RequestMapping(value = "/toLogin")
	public String toLogin() {
		return "login";
	}
		
		//获取图片列表
		@RequestMapping("/listImages")
		public ModelAndView list(Model model) {
			List<User> lists = userService.list();
			ModelAndView mav = new ModelAndView();
			mav.addObject("lists", lists);
			mav.setViewName("user/image");
			System.out.println(lists);
			return mav;
		}
		
		/**
		 * 保存图片
		 * @param file
		 * @param user
		 * @param map
		 * @return
		 */
		@RequestMapping(value="/save",method = RequestMethod.POST)
		public String save(MultipartFile file, User user, ModelMap map) {
			System.out.println(file);
			try {
				return userService.save(file, user, map);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//跳转到添加图片页面
		@RequestMapping(value = "/toAddImage")
		public String toAddImage(HttpServletRequest request) {
			return "user/addimage";
		}
		
		//跳转我的桌面页面
		@RequestMapping(value = "/toMyHome")
		public String toMyHome(HttpServletRequest request,Model model) {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");
			String uId = String.valueOf(user.getUid());
			
			if (0 == user.getUrole()) {
				//管理员
				uId = "";
			}
			
			model.addAttribute("preSubmit", pawnDao.findAProCount(uId,""));//未提交总数
			model.addAttribute("reviewing", adminDao.countPropertyReview(uId));//待审核总数
			model.addAttribute("postLoan", adminDao.countPostLoanList(uId));//贷后管理总数
			
			return "myHome";
		}
		
		//跳转到显示图片页面
		@RequestMapping(value = "/toImage")
		public String toImage(HttpServletRequest request) {
			return "user/image";
		}
		
		//修改房贷合同信息
		@RequestMapping(value = "/updatePropertyCon",method = RequestMethod.POST)
		@ResponseBody
		public void updatePropertyCon(@RequestBody Contract pContract) {
			pContract.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			System.out.println("【要修改的房贷合同信息为】tb_contract:"+pContract);
			userService.updatePropertyCon(pContract);
		}
		
		//管理员审核提交
		@RequestMapping(value = "/submitReview",method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> submitReview(@RequestBody Map<String, String> map) {
			return userService.submitReview(map);
		}
		
		/**
		 * 贷后状态更新
		 * @return
		 */
		@PostMapping("/updateStatus")
		@ResponseBody
		public String updateStatus(@RequestBody TbPostLoanRecord tbPostLoanRecord) {
			return userService.updateStatus(tbPostLoanRecord);
		}
		
		/**
		 * 跳转还款计划
		 * @param repayMethod
		 * @return
		 */
		@RequestMapping(value = "/toCalculatePage")
		public String toCalculatePage(String balance,
									  String interest,
									  String repayMethod,
									  Date beginDay,
									  String term,
									  Date endDateInput,
									  Model model){
			
			Contract contract = new Contract();
			
			contract.setBalance(balance);
			contract.setInterest(interest);
			contract.setRepayMethod(repayMethod);
			contract.setBeginDay(beginDay);
			contract.setTerm(term);
			contract.setEndDateInput(endDateInput);
			
			model.addAttribute("contract", contract);
			
			return "user/applyRepay";
		}
		
}
