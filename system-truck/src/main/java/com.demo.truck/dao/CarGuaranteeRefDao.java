
package com.demo.truck.dao;

import com.demo.truck.Dto.GuaranteeInfoDto;

/**
 * 
 * @author demo
 * @date 2017年7月7日
 */
public interface CarGuaranteeRefDao {

	/**
	 * save new car_guarantee info 
	 *@Date 2017年7月7日
	 * @param guaranteeInfoDto
	 */
	public void saveCarGuaranteeRef(GuaranteeInfoDto guaranteeInfoDto);
}
