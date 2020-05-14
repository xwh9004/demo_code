package com.demo.truck.bean;

import com.demo.truck.common.QueryPageBase;
/**
 * 
 * @author Administrator
 * @date 2017年7月6日
 */
@SuppressWarnings("rawtypes")
public class CarInfoQueryBean extends QueryPageBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6580862490864438327L;

	private String ownerName;
	
	private String brand;
	
	private Double tonnage;
	
	private Integer cardColor;
	
	private String companyCode;
	
	private Integer status;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
