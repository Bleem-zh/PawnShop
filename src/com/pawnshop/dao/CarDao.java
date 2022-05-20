package com.pawnshop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pawnshop.po.TbCar;

public interface CarDao {

	//查询汽车未提交的列表数据
	//public List<Object> getCarContractList(@Param("page")int page, @Param("limit")int limit);

	//统计汽车未提交的列表总数
	public int countCarContractList();

	/**
	 * 获取汽车列表界面表单数据
	 * @param page
	 * @param limit
	 * @param drivingLicense
	 * @return
	 */
	public List<TbCar> getCarList(Map<String, Object> parameterMap);
	
	/**
	 * 统计汽车表的数量
	 * @return
	 */
	public Integer countTbCar();

	/**
	 * 新增一个汽车信息
	 * @param tbCar
	 */
	public void insertCar(TbCar tbCar);

	/**
	 * 修改汽车信息
	 * @param tbCar
	 * @return
	 */
	public int updateCar(TbCar tbCar);

	/**
	 * 
	 * @param carId
	 * @return
	 */
	public int findTbCarApply(String carId);
	
}
