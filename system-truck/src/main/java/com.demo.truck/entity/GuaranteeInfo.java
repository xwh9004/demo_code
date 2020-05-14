package com.demo.truck.entity;

import java.util.Date;

import com.demo.truck.common.BaseEntity;

public class GuaranteeInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4700836375062921436L;
	
	private Integer id;
	
	private String guaranteeNo;
	//商业保险起始时间
	private Date commercialInsuranceFrom;
	//商业保险结束时间
	private Date commercialInsuranceTo;
	//强制险起始时间
	private Date compulsoryInsuranceFrom;
	//强制险结束时间
	private Date compulsoryInsuranceTo;
	//第三责任险金额（理赔最大金额）
	private Double commercialInsuranceCoverage;
	//商业保险金额
	private Double commercialInsurancePremium;
	//强制险金额
	private Double compulsoryInsurancePremium;
	//车船税
	private Double travelTax;
	//审证费
	private Double verifiyCreditCost;
	//总花费
	private Double totalCost;
	//办理人
	private String transactor;
	
	private Integer status;
	
	private String remark;
	
	private Integer isDel;

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

	public Date getCompulsoryInsuranceFrom() {
		return compulsoryInsuranceFrom;
	}

	public void setCompulsoryInsuranceFrom(Date compulsoryInsuranceFrom) {
		this.compulsoryInsuranceFrom = compulsoryInsuranceFrom;
	}

	public Date getCompulsoryInsuranceTo() {
		return compulsoryInsuranceTo;
	}

	public void setCompulsoryInsuranceTo(Date compulsoryInsuranceTo) {
		this.compulsoryInsuranceTo = compulsoryInsuranceTo;
	}

	public Double getCommercialInsuranceCoverage() {
		return commercialInsuranceCoverage;
	}

	public void setCommercialInsuranceCoverage(Double commercialInsuranceCoverage) {
		this.commercialInsuranceCoverage = commercialInsuranceCoverage;
	}

	public Double getCommercialInsurancePremium() {
		return commercialInsurancePremium;
	}

	public void setCommercialInsurancePremium(Double commercialInsurancePremium) {
		this.commercialInsurancePremium = commercialInsurancePremium;
	}

	public Double getCompulsoryInsurancePremium() {
		return compulsoryInsurancePremium;
	}

	public void setCompulsoryInsurancePremium(Double compulsoryInsurancePremium) {
		this.compulsoryInsurancePremium = compulsoryInsurancePremium;
	}

	public Double getTravelTax() {
		return travelTax;
	}

	public void setTravelTax(Double travelTax) {
		this.travelTax = travelTax;
	}

	public Double getVerifiyCreditCost() {
		return verifiyCreditCost;
	}

	public void setVerifiyCreditCost(Double verifiyCreditCost) {
		this.verifiyCreditCost = verifiyCreditCost;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
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
