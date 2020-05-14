package com.demo.truck.bean;

import com.demo.truck.common.QueryPageBase;
/**
*@Date 2017年7月6日
*
**/
@SuppressWarnings("rawtypes")
public class OwnerQueryBean extends QueryPageBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2826781687736580384L;

	private  String ownerName;
	
	private String idNo;
	
	private String telephone;
	
	private String companyCode;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	
	
}
