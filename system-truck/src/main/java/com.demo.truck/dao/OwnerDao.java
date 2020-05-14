package com.demo.truck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.truck.bean.OwnerQueryBean;
import com.demo.truck.entity.Owner;
/**
 * 
 * @author demo
 * @date 2017-7-5
 *
 */
public interface OwnerDao {

	/**
	 * save new owner
	 * @param owner
	 * @Date 2017-7-6
	 */
	public void saveOwner(Owner owner);
	/**
	 * fuzzy  querry owner list by queryBean
	 * @param name
	 * @return
	 */
	public List<Owner> findOwnerList(OwnerQueryBean queryBean);
	
	/**
	 * fuzzy query owner list count by queryBean
	 *@Date 2017年7月7日
	 * @param queryBean
	 * @return
	 */
	public Integer findOwnerListCount(OwnerQueryBean queryBean);
	
	/**
	 * querry owner by IdNo
	 * @param IdNO
	 * @return Owner
	 */
	public Owner findOwnerById(@Param( value = "id" ) Integer id);
	/**
	 * 更新用户信息
	 *@Date 2017年7月15日
	 * @param owner
	 **/
	public void updateOwner(Owner owner);
}
