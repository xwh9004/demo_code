package com.demo.truck.entity;

import java.util.Date;

import com.demo.truck.common.BaseEntity;

public class CarCompesationRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4164963911592061344L;
	
	private int id;
	private int carId;
	private String claimAdjuster;
	private double claimAmount;
	private Date claimTime;
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
	public String getClaimAdjuster() {
		return claimAdjuster;
	}
	public void setClaimAdjuster(String claimAdjuster) {
		this.claimAdjuster = claimAdjuster;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public Date getClaimTime() {
		return claimTime;
	}
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
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
