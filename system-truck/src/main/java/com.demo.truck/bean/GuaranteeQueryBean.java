package com.demo.truck.bean;
import java.util.Date;

import com.demo.truck.common.QueryPageBase;
/**
 * 保单查询bean
 * @author demo
 * @date 2017年7月7日
 */
@SuppressWarnings("rawtypes")
public class GuaranteeQueryBean extends QueryPageBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8764196584712088875L;
	
	private String guaranteeNo;
	
	private String carNo;
	
	private String ownerName;
	
	private Date commercialInsuranceFrom; 
	
	private Date insuranceExpireFrom; 
	
	private Date insuranceExpireTo; 
	
	private Integer status;
	
	private String companyCode;
	
	private Integer quickType;

	public String getGuaranteeNo() {
		return guaranteeNo;
	}

	public void setGuaranteeNo(String guaranteeNo) {
		this.guaranteeNo = guaranteeNo;
	}

	

	public Integer getQuickType() {
		return quickType;
	}

	public void setQuickType(Integer quickType) {
		this.quickType = quickType;
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

	public Date getInsuranceExpireFrom() {
		return insuranceExpireFrom;
	}

	public void setInsuranceExpireFrom(Date insuranceExpireFrom) {
		this.insuranceExpireFrom = insuranceExpireFrom;
	}

	public Date getInsuranceExpireTo() {
		return insuranceExpireTo;
	}

	public void setInsuranceExpireTo(Date insuranceExpireTo) {
		this.insuranceExpireTo = insuranceExpireTo;
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
	
	

}
