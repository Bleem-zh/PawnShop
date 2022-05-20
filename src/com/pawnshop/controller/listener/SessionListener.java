package com.pawnshop.controller.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pawnshop.controller.LoginController;
import com.pawnshop.dao.LoginDao;
import com.pawnshop.po.User;
import com.pawnshop.service.LoginService;
import com.pawnshop.service.impl.LoginServiceImpl;

@ComponentScan
public class SessionListener implements HttpSessionListener{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.toString());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		User user = (User) session.getAttribute("user");
		
		if (!ObjectUtils.isEmpty(user)) {
			LoginDao loginDao = WebApplicationContextUtils.getWebApplicationContext(arg0.getSession().getServletContext()).getBean(LoginDao.class);
			
			//删除该登录记录
			loginDao.delLogin(user.getUid());
			
			// TODO Auto-generated method stub
			System.out.println("【用户退出】-------->"+user.toString());
			logger.info("【用户退出】-------->"+user.toString());
		}
	}

}
