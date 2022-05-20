package com.pawnshop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pawnshop.po.User;


public interface LoginService {

	public User findUser(String usernameName,String password);
	public void regist(String username,String password);
	public List<User> checkRepeat(String username);
	
	//登录
	public String login(HttpServletRequest request, User user);
}
