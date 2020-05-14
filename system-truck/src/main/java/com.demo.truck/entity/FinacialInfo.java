package com.demo.truck.entity;

import com.demo.truck.common.BaseEntity;

public class FinacialInfo extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1704413045513303980L;

	private int id;
	
	private int carId;
	
	private int contractId;
	
	private int guaranteeId;
	
	private double otherExpense;
	
	private double insuranceExpense;
	
	private double profit;
	
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

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public int getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(int guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public double getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(double otherExpense) {
		this.otherExpense = otherExpense;
	}

	public double getInsuranceExpense() {
		return insuranceExpense;
	}

	public void setInsuranceExpense(double insuranceExpense) {
		this.insuranceExpense = insuranceExpense;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
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
