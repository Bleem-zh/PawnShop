package com.pawnshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pawnshop.common.CommonUtils;
import com.pawnshop.dao.LoginDao;
import com.pawnshop.po.TbLogin;
import com.pawnshop.po.TbLoginRecord;
import com.pawnshop.po.User;
import com.pawnshop.service.LoginService;
import com.pawnshop.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginDao loginDao;
	
	/**
	 * 重定向到index
	 * @return
	 */
	@RequestMapping(value = "/toIndex")
	public String toIndex(){
		return "redirect:/";
	}

	//登录操作
	@RequestMapping(value = "/loginIn",produces={"text/html;charset=UTF-8;","application/json;"})
	@ResponseBody
	public String login(@RequestBody User loginUser,Model model,HttpServletRequest request) {
		String usernameName = loginUser.getUsernameName();
		String password = loginUser.getPassword();
				
		User user = loginService.findUser(usernameName, password);         //根据传入的账号和密码去数据库中查用户
		
		model.addAttribute("user",user);
		if(usernameName==null||usernameName.equals("")||password==null||password.equals("")){
			model.addAttribute("info","用户名或密码不能为空");
		}
		
		if(ObjectUtils.isEmpty(user)){
			System.out.println("验证失败--->"+user);
			return "-1";
		}else if(user.getUrole()==0 || user.getUrole()==1){
			if(user.getUrole()==0)
				System.out.println("验证用户身份为管理员");
			else
				System.out.println("验证用户身份为普通用户");
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);                    //将查到的用户信息保存到session中
			//session.setMaxInactiveInterval(-1);
			
			//session相关操作
			String loginMsg = loginService.login(request,user);
			if (!StringUtils.isEmpty(loginMsg)) {
				return JSON.toJSONString(loginMsg);
			}
			
			return String.valueOf(user.getUrole());
		}else{
			System.out.println("验证失败");
			return "-1";
		}
	}
	
	
//   注册
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	@ResponseBody
	public int register(@RequestBody User user, Model model,HttpSession session){
		List<User> userList = this.loginService.checkRepeat(user.getUsername());
		if(userList.isEmpty()){
			loginService.regist(user.getUsername(), user.getPassword());
			return 1;
		}
		model.addAttribute("message","用户名重复");
		return 2;
	}
	
	//跳转到主页面(已登录)
	@RequestMapping(value = "/toMainlogined")
	public String toMain() {
		
//		System.out.println(request.getParameter("username"));
		return "mainlogined";
	}
	
	//跳转到主页面(未登录)
	@RequestMapping(value = "/toMainunlogin")
	public String toMainunlogin() {
		
//		System.out.println(request.getParameter("username"));
		return "mainunlogin";
	}
	
	//跳转到管理员页面
	@RequestMapping(value = "/toAdminMain")
	public String toAdminMain() {
		return "admin/adminMain";
	}
	//跳转到登录页面
	@RequestMapping(value = "/toLogin")
	public String toLogin() {
		return "index";
	}
	
	//退出操作
	@RequestMapping(value = "/loginOut")
	public void loginOut(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(false);//获取不到就返回空
		if (!ObjectUtils.isEmpty(session)){
			session.invalidate();//注销
		}
		
		try {
			response.sendRedirect("/PawnShop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "index";
	}
	
}
