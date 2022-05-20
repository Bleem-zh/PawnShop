package com.pawnshop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.pawnshop.common.CommonUtils;
import com.pawnshop.dao.CarDao;
import com.pawnshop.po.TbCar;
import com.pawnshop.service.CarService;

@Service("carService")
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDao carDao;

	//@Override
/*	public Map<String, Object> getCarContractList(int page, int limit) {
		Map<String, Object> result = new HashMap<String, Object>();
	    result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",carDao.countCarContractList());
	    Object data=JSON.toJSON(carDao.getCarContractList(page, limit));
	    System.out.println(data);
	    result.put("data", data);
		
		return result;
	}*/

	@Override
	public Map<String, Object> getCarList(int page, int limit,
			String drivingLicense) {
		TbCar car = new TbCar();
		car.setDrivingLicense(drivingLicense);
		
		Map<String, Object> result = new HashMap<String, Object>();
	    result.put("code", 0);
	    result.put("msg", "");
	    result.put("count",carDao.countTbCar());
	    result.put("data", getCarListMethods(page,limit,car));
	    
	    System.out.println("【获取汽车表的所有数据】"+result);
		
		return result;
	}

	@Override
	public Map<String, Object> insertCar(TbCar tbCar) {
		Map<String, Object> resultMap = new HashMap<>();
		
		//查询该汽车是否已经存在
		List<TbCar> carList = getCarListMethods(0, 1, tbCar);
		if (carList.size() > 0) {
			resultMap.put("msg", "此行驶证或发动机号已存在!");
			resultMap.put("status", "9999");
		}else {
			tbCar.setCarId(CommonUtils.getDateId());
			tbCar.setUpdateTime(CommonUtils.getNowTimestamp());
			
			carDao.insertCar(tbCar);
		}
		
		return resultMap;
	}


	@Override
	public String toCar(String carId, String editFlag, Model model) {
		model.addAttribute("editFlag", editFlag);
		
		if(!StringUtils.isEmpty(carId)){
			TbCar car = new TbCar();
			car.setCarId(carId);
			
			//获取汽车界面数据信息
			List<TbCar> carList = getCarListMethods(0, 1, car);
			car = carList.get(0);
			
			model.addAttribute("car", car);
		}
		
		return "productEdit/car/car";
	}

	@Override
	public List<TbCar> getCarListMethods(int page, int limit, TbCar car) {
		Map<String, Object> paramterMap = new HashMap<>();
		paramterMap.put("page", page);
		paramterMap.put("limit", limit);
		paramterMap.put("car", car);
		
		return carDao.getCarList(paramterMap);
	}

	@Override
	public Map<String, Object> updateCar(TbCar tbCar) {
		Map<String, Object> resultMap = new HashMap<>();
		
		int findTbCarApply = carDao.findTbCarApply(tbCar.getCarId());
		
		//查看是否有在途
		if (carDao.findTbCarApply(tbCar.getCarId()) > 0) {
			resultMap.put("msg", "此抵押车有尚未审核通过的申请,暂不可修改!");
			resultMap.put("status", "9999");
			return resultMap;
		}
		
		if(1 != carDao.updateCar(tbCar)){
			resultMap.put("msg", "修改失败!");
			resultMap.put("status", "9999");
		}
		
		return resultMap;
	}
}
