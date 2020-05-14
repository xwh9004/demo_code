
package com.demo.truck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.bean.GuaranteeBean;
import com.demo.truck.bean.GuaranteeQueryBean;

/**
 * 
 * @author demo
 * @date 2017年7月7日
 */
public interface GuaranteeInfoDao {
	
	/**
	 * save new guaranteeInfo
	 *@Date 2017年7月7日
	 * @param guaranteeInfoDto
	 */
	public void savaGuaranteeInfo(GuaranteeInfoDto guaranteeInfoDto);
	
	/**
	 * 查询保单列表
	 *@Date 2017年7月7日
	 * @param queryBean
	 * @return
	 */
	public List<GuaranteeBean> findGuaranteeList(GuaranteeQueryBean queryBean);
	
	/**
	 * 查询保单列表数量
	 *@Date 2017年7月7日
	 * @param queryBean
	 * @return
	 */
	public Integer findGuaranteeListCount(GuaranteeQueryBean queryBean);
	
	/**
	 * 查询保单详情
	 *@Date 2017年7月7日
	 * @param guaranteeId
	 */
	public GuaranteeInfoDto findGuaranteeDetailById(@Param( value = "guaranteeId" ) Integer guaranteeId);
	/**
	 * 修改保单记录
	 *@Date 2017年7月14日
	 * @param guaranteeInfoDto
	 */
	public void updateGuaranteeInfo(GuaranteeInfoDto guaranteeInfoDto);

}
