package com.pawnshop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pawnshop.po.TbCar;
import com.pawnshop.po.User;
import com.pawnshop.service.CarService;

@Controller
@RequestMapping(value = "/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	/**
	 * 跳转汽车信息界面
	 * @return
	 */
	@GetMapping("/toCar")
	public String toCar(String carId,String editFlag,Model model){
		return carService.toCar(carId,editFlag,model);
	}
	
	/**
	 * 跳转汽车管理界面
	 * @return
	 */
	@GetMapping("/toCarManagement")
	public String toCarManagement(){
		return "productEdit/car/manage_car";
	}
	
	/**
	 * 跳转添加抵押车的界面
	 * @return
	 */
	@GetMapping("/toAddCarList")
	public String toAddCarList(){
		return "productEdit/car/addList_car";
	}
	
	/**
	 * 获取汽车列表界面表单数据
	 * @return
	 */
	@GetMapping("/getCarList")
	@ResponseBody
	public Map<String, Object> getCarList(@RequestParam int page,
			@RequestParam int limit,String drivingLicense){
		return carService.getCarList((page-1)*limit, limit,drivingLicense);
	}
	
	/**
	 * 新增汽车
	 * @return
	 */
	@PostMapping("/insertCar")
	@ResponseBody
	public Map<String, Object> insertCar(@RequestBody TbCar tbCar,HttpSession session){
		User user = (User) session.getAttribute("user");
		tbCar.setUpdater(String.valueOf(user.getUid()));
		
		return carService.insertCar(tbCar);
		//return carService.getCarList((page-1)*limit, limit,drivingLicense);
	}
	
	/**
	 * 修改汽车信息
	 * @return
	 */
	@PostMapping("/updateCar")
	@ResponseBody
	public Map<String, Object> updateCar(@RequestBody TbCar tbCar,HttpSession session){
		User user = (User) session.getAttribute("user");
		tbCar.setUpdater(String.valueOf(user.getUid()));
		
		return carService.updateCar(tbCar);
	}
	
	/**
	 * 获取汽车管理界面表单数据
	 * @return
	 */
	/*@GetMapping("/getCarContractList")
	@ResponseBody
	public Map<String, Object> getCarContractList(@RequestParam int page,@RequestParam int limit){
		return carService.getCarContractList((page-1)*limit, limit);
	}*/
}
