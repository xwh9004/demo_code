
package com.demo.truck.dao;

import java.util.List;

import com.demo.truck.entity.OrgInfo;

/**
 * 组织结构dao
 * @author Administrator
 * @date 2017年7月21日
 */
public interface OrgInfoDao {
	/**
	 * 新增组织结构
	 *@Date 2017年7月21日
	 */
	public void addOrgInfo(OrgInfo orgInfo);
	/**
	 * 查询所有组织结构信息
	 *@Date 2017年7月21日
	 * @return
	 */
	public List<OrgInfo> findCompanyOrgs();
	/**
	 * 删除组织结构
	 *@Date 2017年7月21日
	 * @param orgInfo
	 */
	public void deleteOrgInfo(OrgInfo orgInfo);

}
