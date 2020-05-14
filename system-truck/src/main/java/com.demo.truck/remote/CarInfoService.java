package com.demo.truck.remote;

import java.util.List;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.bean.CarInfoDetailBean;
import com.demo.truck.bean.CarInfoQueryBean;
import com.demo.truck.entity.CarCompesationRecord;
import com.demo.truck.entity.CarValidateRecord;
import com.demo.truck.exception.ServiceException;

/**
 * 
 * @author demo
 * @date 2017年7月6日
 */
public interface CarInfoService {

	/**
	 * 新增车辆
	 * 
	 * @Date 2017年7月6日
	 * @param carInfoDto
	 * @return
	 */
	public void saveCarInfo(CarInfoDto carInfoDto) throws ServiceException;

	/**
	 * 
	 * @Date 2017年7月7日
	 * @return
	 */
	public CarInfoQueryBean findCarInfoList(CarInfoQueryBean queryBean) throws ServiceException;

	/**
	 * 根据id查询车辆详细信息
	 * 
	 * @Date 2017年7月10日
	 * @param id
	 * @return
	 */
	public CarInfoDetailBean findCarInfoDetailById(Integer id) throws ServiceException;

	/**
	 * 根据id查询车辆车主信息
	 * 
	 * @Date 2017年7月10日
	 * @param id
	 * @return
	 */
	public CarInfoDto findCarInfoDtoById(Integer id) throws ServiceException;

	/**
	 * 修改车辆信息
	 * 
	 * @Date 2017年7月13日
	 * @param carInfoDto
	 */
	public void submitCarInfo(CarInfoDto carInfoDto)  throws ServiceException;

	/**
	 * 车辆新增验车记录
	 * 
	 * @Date 2017年7月16日
	 * @param record
	 */
	public void addCarValidateRecordList(List<CarValidateRecord> recordList,Integer carId) throws ServiceException;

	/**
	 * 车辆新增出险记录
	 * @Date 2017年7月16日
	 * @param record
	 */
	public void addCarCompesationRecordList(List<CarCompesationRecord> recordList,Integer carId) throws ServiceException;
}
