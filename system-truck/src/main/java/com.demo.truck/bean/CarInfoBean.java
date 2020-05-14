package com.demo.truck.bean;

import java.io.Serializable;

/**
 * 车辆列表信息显示bean
 * 
 * @author demo
 * @date 2017年7月7日
 */
public class CarInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5290646916765267282L;

	private Integer id;

	private String ownerName;
	
	private String carNo;

	private String brand;

	private Double tonnage;

	private Integer cardColor;

	private String companyCode;

	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	

}
