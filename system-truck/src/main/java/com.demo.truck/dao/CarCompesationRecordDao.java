
package com.demo.truck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.truck.entity.CarCompesationRecord;

/**
 * 车辆出险记录dao
 * 
 * @author Administrator
 * @date 2017年7月16日
 */
public interface CarCompesationRecordDao {
	/**
	 * 新增车辆出现记录
	 *@Date 2017年7月16日
	 */
	public void addCompesationRecords(@Param( value = "recordList" ) List<CarCompesationRecord> recordList, @Param( value = "carId" ) Integer carId);
}
