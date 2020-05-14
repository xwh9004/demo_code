package com.demo.truck.remote;

import org.apache.ibatis.annotations.Update;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.bean.OwnerQueryBean;
import com.demo.truck.entity.Owner;

public interface OwnerService {
	/**
	 * 
	 * @Date 2017年7月6日
	 * @param carInfoDto
	 */
	public boolean saveOwner(Owner owner);

	/**
	 * 
	 * @Date 2017年7月6日
	 * @return
	 */
	public OwnerQueryBean findOwnersList(OwnerQueryBean queryBean);

	/**
	 * 根据idNo查询车主信息
	 * @Date 2017年7月6日
	 * @param idNO
	 * @return
	 */
	public Owner findOwnerById(Integer id);
}
