
package com.demo.truck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.truck.entity.CarValidateRecord;

public interface CarValidateRecordDao {
	/**
	 * 新增车辆维修记录
	 *@Date 2017年7月16日
	 * @param record
	 */
   public void addRecords(@Param( value = "recordList" ) List<CarValidateRecord> recordList, @Param( value = "carId" ) Integer carId) ;
}
