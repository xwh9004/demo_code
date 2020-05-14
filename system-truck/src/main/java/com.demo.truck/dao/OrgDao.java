
package com.demo.truck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.truck.entity.OrgInfo;

/**
 * 组织结构dao
 * @author Administrator
 * @date 2017年7月23日
 */
public interface OrgDao {
	/**
	 * 新增组织节点
	 *@Date 2017年7月23日
	 * @param orgInfo
	 */
	public void  saveOrg(OrgInfo orgInfo);
	/**
	 * 删除父节点为pId的所有子节点的组织
	 *@Date 2017年7月23日
	 * @param orgInfo
	 */
	public void  deleteOrgByPid(Integer pId);
	/**
	 * 删除组织节点为id的节点
	 *@Date 2017年7月23日
	 * @param id
	 */
	public void deleteOrgById(Integer id);
	/**
	 * 查询当前组织节点的一级子节点,传入参数为null则查询所有顶级节点
	 *@Date 2017年7月23日
	 * @param parentId  
	 * @return
	 */
	public List<OrgInfo> findChildsBy(@Param( value = "parentId" ) Integer parentId);


}
