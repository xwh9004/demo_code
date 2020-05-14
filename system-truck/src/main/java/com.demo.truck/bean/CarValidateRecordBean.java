package com.demo.truck.bean;

import java.io.Serializable;
import java.util.Date;

public class CarValidateRecordBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7960608228796787651L;
	private Integer id;
	private Integer carId;
	private Date validateTime;
	private Integer validateType;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public Date getValidateTime() {
		return validateTime;
	}
	public void setValidateTime(Date validateTime) {
		this.validateTime = validateTime;
	}
	public Integer getValidateType() {
		return validateType;
	}
	public void setValidateType(Integer validateType) {
		this.validateType = validateType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
