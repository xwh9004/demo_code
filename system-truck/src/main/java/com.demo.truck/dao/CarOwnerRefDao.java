package com.demo.truck.dao;

import com.demo.truck.Dto.CarInfoDto;
/**
 * 
 * @author demo
 * @date 2017年7月6日
 */
public interface CarOwnerRefDao {

	/**
	 * 
	 *@Date 2017年7月6日
	 * @param carInfoDto
	 */
	public void saveCarOwnerRef(CarInfoDto carInfoDto);
	
	/**
	 * 修改车辆车主关系
	 *@Date 2017年7月13日
	 * @param carInfoDto
	 */
	public void updateCarOwnerRef(CarInfoDto carInfoDto);
}
