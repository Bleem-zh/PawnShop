package com.pawnshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pawnshop.dao.UtilsDao;
import com.pawnshop.po.Loaner;

@Controller
@RequestMapping(value = "/others")
public class OtersController {
	@Autowired
	private UtilsDao utilsDao;
	
	// 获取空列表
	@RequestMapping(value = "/getNullList",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getNullList() {
		Map<String, Object> result = new HashMap<String, Object>();
	    result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",0);
	    result.put("data", null);
	    
		return result;
	}
	
	
		
}
