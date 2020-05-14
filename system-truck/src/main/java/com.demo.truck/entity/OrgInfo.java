
package com.demo.truck.entity;

import com.demo.truck.common.BaseEntity;

public class OrgInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3198527194634741348L;

	private Integer id;

	private Integer parentId;

	private String orgName;
	
	private String orgCode;
	
	private Integer orgType;
	
	private String orgDesc;
	
	private Integer isDel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		return this.orgName.toString();
	}
	

}
