package com.pawnshop.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawnshop.common.CommonUtils;
import com.pawnshop.dao.LoginDao;
import com.pawnshop.po.TbLogin;
import com.pawnshop.po.TbLoginRecord;
import com.pawnshop.po.User;
import com.pawnshop.service.LoginService;

@Service("loginService")
@Transactional

public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;
	@Override
	public User findUser(String usernameName, String password) {
		return this.loginDao.findUser(usernameName, password);
	}
	@Override
	public void regist(String username, String password) {
		// TODO Auto-generated method stub
		this.loginDao.regist(username, password);
	}
	@Override
	public List<User> checkRepeat(String username) {
		// TODO Auto-generated method stub
		return loginDao.checkRepeat(username);
	}
	@Override
	public String login(HttpServletRequest request,User user) {
		//String macAddress = CommonUtils.getMACAddress(CommonUtils.getIpAddress(request));
		String ipAddress = CommonUtils.getIpAddress(request);
		
		//保存登录记录
		TbLoginRecord loginRecord = new TbLoginRecord();
		loginRecord.setLoginIp(ipAddress);
		loginRecord.setUpdateTime(CommonUtils.getNowTimestamp());
		loginRecord.setuId(String.valueOf(user.getUid()));
		
		//查询本机是否已经登录有账号和登录的账号是否存在session中
		List<TbLogin> loginList= loginDao.findLogin(loginRecord);
		//该账号或IP已经存在
		if (loginList.size() > 0) {
			for (TbLogin tbLogin : loginList) {
				//先检查本机IP
				if (ipAddress.equalsIgnoreCase(tbLogin.getLoginIp()) 
						&& !String.valueOf(user.getUid()).equalsIgnoreCase(tbLogin.getuId())) {
					return "本机已经登录"+tbLogin.getUserName();
				}else if (!ipAddress.equalsIgnoreCase(tbLogin.getLoginIp()) 
						&& String.valueOf(user.getUid()).equalsIgnoreCase(tbLogin.getuId())) {
					//检查账号	
					return "此账号已在【"+tbLogin.getLoginIp()+"】登录";
				}else if (ipAddress.equalsIgnoreCase(tbLogin.getLoginIp()) 
						&& String.valueOf(user.getUid()).equalsIgnoreCase(tbLogin.getuId())){
					//本机已经登录了这个账号，直接给登录
					//保存登录记录
					loginDao.savetLoginRecord(loginRecord);
					System.out.println("【新增登录记录】-->"+loginRecord);
					
					return "";
				}
			}
		}
		
		//session相关操作，一个IP只允许登录一个账号
		TbLogin tbLogin = new TbLogin();
		
		BeanUtils.copyProperties(loginRecord, tbLogin);
		tbLogin.setCreateTime(loginRecord.getUpdateTime());
		tbLogin.setUserName(user.getUsername());
		
		loginDao.saveLogin(tbLogin);
		
		//保存登录记录
		loginDao.savetLoginRecord(loginRecord);
		
		System.out.println("【新增登录记录】-->"+loginRecord);
		return "";
		
	}
	
	
}