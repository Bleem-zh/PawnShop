package com.pawnshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.pawnshop.po.TbLogin;
import com.pawnshop.po.TbLoginRecord;
import com.pawnshop.po.User;

@Component
public interface LoginDao {

	public User findUser(@Param("usernameName") String usernameName,
			             @Param("password")  String password);
	public void regist(@Param("username") String username,
			             @Param("password")  String password);
	public List<User> checkRepeat(@Param("username") String username);
	

	/**
	 * 新增登录记录
	 * @param tbLoginRecord
	 */
	public void savetLoginRecord(TbLoginRecord tbLoginRecord);
	
	/**
	 * 保存登录IP状态
	 * @param loginRecord
	 */
	public void saveLogin(TbLogin tbLogin);
	
	/**
	 * 查询本机是否已经登录有账号和登录的账号是否存在session中
	 * @param loginRecord
	 * @return
	 */
	public List<TbLogin> findLogin(TbLoginRecord loginRecord);
	
	/**
	 * 删除登录记录
	 * @param uid
	 */
	@Bean
	public void delLogin(Integer uid);
}
