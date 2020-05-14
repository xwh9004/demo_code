package com.demo.truck.bean;

import java.io.Serializable;
import java.util.Date;

public class CompesationRecordBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4064813936142799009L;
	
	private Integer id;
	private Integer carId;
	private String claimAdjuster;
	private Double claimAmount;
	private Date claimTime;
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
	public String getClaimAdjuster() {
		return claimAdjuster;
	}
	public void setClaimAdjuster(String claimAdjuster) {
		this.claimAdjuster = claimAdjuster;
	}
	public Double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public Date getClaimTime() {
		return claimTime;
	}
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
