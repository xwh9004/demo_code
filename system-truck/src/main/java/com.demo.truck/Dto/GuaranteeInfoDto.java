
package com.demo.truck.Dto;

import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.GuaranteeInfo;
import com.demo.truck.entity.Owner;

/**
 * 保单的详细信息
 * @author demo
 * @date 2017年7月7日
 */
public class GuaranteeInfoDto extends GuaranteeInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3013701894610335774L;
	
	private Integer id;
	//车辆挂靠合同id
	private Integer contractId;
	//车辆挂靠合同编号
	private String contractNo;
	//保单guaranteeId;
	private Integer guaranteeId;
	//保单车辆信息
	private CarInfo carInfo;
	
	private Owner owner;

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		contractId = contractId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		contractNo = contractNo;
	}

	public Integer getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(Integer guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	
	
	

}
