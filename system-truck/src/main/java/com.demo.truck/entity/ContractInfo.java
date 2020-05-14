package com.demo.truck.entity;

import com.demo.truck.common.BaseEntity;

public class ContractInfo extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6292819813485065916L;

	private int id;
	
	private int carId;
	
	private String contractNo;
	
	private double manageExpense;
	
	private double deposit;
	
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public double getManageExpense() {
		return manageExpense;
	}

	public void setManageExpense(double manageExpense) {
		this.manageExpense = manageExpense;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
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
