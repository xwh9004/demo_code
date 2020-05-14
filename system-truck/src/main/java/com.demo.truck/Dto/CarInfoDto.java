package com.demo.truck.Dto;

import com.demo.truck.common.BaseEntity;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.Owner;
/**
 * 车辆的详细信息
 * @author demo
 * @date 2017年7月6日
 */
public class CarInfoDto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8337095283712998816L;
	
	private Integer id;
	
	private CarInfo carInfo;
	
	//车主信息
	private Owner owner;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
