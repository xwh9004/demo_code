package com.demo.truck.entity;

import java.util.Date;

import com.demo.truck.common.BaseEntity;

public class CarValidateRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1224303297194271797L;

	private int id;
	private int carId;
	private Date validateTime;
	private int validateType;
	private int isDel;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public Date getValidateTime() {
		return validateTime;
	}
	public void setValidateTime(Date validateTime) {
		this.validateTime = validateTime;
	}
	public int getValidateType() {
		return validateType;
	}
	public void setValidateType(int validateType) {
		this.validateType = validateType;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
