package com.pawnshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.pawnshop.po.TbCar;

public interface CarService {
	/**
	 * 获取汽车未提交列表数据
	 * @param page
	 * @param limit
	 * @return
	 */
	/*public Map<String, Object> getCarContractList(int page,int limit);*/

	/**
	 * 获取汽车列表界面表单数据
	 * @param page
	 * @param limit
	 * @param drivingLicense
	 * @return
	 */
	public Map<String, Object> getCarList(int page, int limit,
			String drivingLicense);

	/**
	 * 新增汽车
	 * @param tbCar
	 * @return
	 */
	public Map<String, Object> insertCar(TbCar tbCar);

	/**
	 * 获取汽车的列表，可以带参数也可以不带参数
	 * @param page
	 * @param limit
	 * @param car
	 * @return
	 */
	public List<TbCar> getCarListMethods(int page, int limit,TbCar car);

	/**
	 * 跳转汽车界面信息
	 * @param carId
	 * @param editFlag
	 * @param model
	 * @return
	 */
	public String toCar(String carId, String editFlag, Model model);

	/**
	 * 修改汽车信息
	 * @param tbCar
	 * @return
	 */
	public Map<String, Object> updateCar(TbCar tbCar);
	
}
