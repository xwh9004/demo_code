package com.demo.truck.bean;


import java.util.List;

import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.Owner;

public class CarInfoDetailBean extends CarInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -100000097861333881L;
	
	private Owner owner;
	
	private List<com.demo.truck.bean.GuaranteeBean> guaranteeRecordList;

	private List<com.demo.truck.bean.CompesationRecordBean> compesationRecordlist;
	
	private List<com.demo.truck.bean.CarValidateRecordBean> carValidateRecordList;

	public List<com.demo.truck.bean.GuaranteeBean> getGuaranteeRecordList() {
		return guaranteeRecordList;
	}

	public void setGuaranteeRecordList(List<com.demo.truck.bean.GuaranteeBean> guaranteeRecordList) {
		this.guaranteeRecordList = guaranteeRecordList;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<com.demo.truck.bean.CompesationRecordBean> getComesationRecordlist() {
		return compesationRecordlist;
	}

	public void setComesationRecordlist(List<com.demo.truck.bean.CompesationRecordBean> compesationRecordlist) {
		this.compesationRecordlist = compesationRecordlist;
	}

	public List<com.demo.truck.bean.CarValidateRecordBean> getCarValidateRecordList() {
		return carValidateRecordList;
	}

	public void setCarValidateRecordBean(List<com.demo.truck.bean.CarValidateRecordBean> carValidateRecordList) {
		this.carValidateRecordList = carValidateRecordList;
	}



}
