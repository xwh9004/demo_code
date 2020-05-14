package com.demo.truck.bean;

import java.io.Serializable;
/**
 * 保单简要信息，用于呈现保单列表
 * @author demo
 * @date 2017年7月7日
 */
import java.util.Date;
public class GuaranteeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313061514984687249L;
	
	private Integer id;
	
	private String guaranteeNo;
	
	private String carNo;
	
	private String ownerName;
	
	private Date commercialInsuranceFrom;
	
	private Date commercialInsuranceTo;
	
	private Double commercialInsurancePremium;
	
	private String transactor;
	
	private Integer status;
	
	private String companyCode;
	
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGuaranteeNo() {
		return guaranteeNo;
	}

	public void setGuaranteeNo(String guaranteeNo) {
		this.guaranteeNo = guaranteeNo;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Date getCommercialInsuranceFrom() {
		return commercialInsuranceFrom;
	}

	public void setCommercialInsuranceFrom(Date commercialInsuranceFrom) {
		this.commercialInsuranceFrom = commercialInsuranceFrom;
	}
 
	public Date getCommercialInsuranceTo() {
		return commercialInsuranceTo;
	}

	public void setCommercialInsuranceTo(Date commercialInsuranceTo) {
		this.commercialInsuranceTo = commercialInsuranceTo;
	}

	public Double getCommercialInsurancePremium() {
		return commercialInsurancePremium;
	}

	public void setCommercialInsurancePremium(Double commercialInsurancePremium) {
		this.commercialInsurancePremium = commercialInsurancePremium;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
