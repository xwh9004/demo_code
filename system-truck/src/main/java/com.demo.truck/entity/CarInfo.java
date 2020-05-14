package com.demo.truck.entity;

import com.demo.truck.common.BaseEntity;

public class CarInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8486140397357564692L;

	private Integer id;
   //车牌号
	private String carNo;
	//厂牌
	private String brand;
	//运营证号
	private String runNo;
	//吨位
	private Double tonnage;
	//车牌颜色
	private Integer cardColor;
	//车辆状态
	private Integer status;
	//备注
	private String remark;
	//是否删除
	private Integer isDel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getRunNo() {
		return runNo;
	}
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}
	public Double getTonnage() {
		return tonnage;
	}
	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}
	public Integer getCardColor() {
		return cardColor;
	}
	public void setCardColor(Integer cardColor) {
		this.cardColor = cardColor;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
}
