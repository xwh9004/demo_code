package com.demo.truck.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.bean.CarInfoBean;
import com.demo.truck.bean.CarInfoDetailBean;
import com.demo.truck.bean.CarInfoQueryBean;
import com.demo.truck.entity.CarInfo;
public interface CarInfoDao {
	
	/**
	 * save new carInfo
	 *@Date 2017年7月6日
	 * @param carInfoDto
	 */
	public void  saveCarInfo(CarInfo carInfo);
	
	/**
	 * query carInfo list by queryBean
	 *@Date 2017年7月7日
	 * @param queryBean
	 * @return
	 */
	public List<CarInfoBean> findCarInfoList(CarInfoQueryBean queryBean);
	
	/**
	 * query carInfo list count by queryBean
	 *@Date 2017年7月7日
	 * @param queryBean
	 * @return
	 */
	public Integer findCarInfoListCount(CarInfoQueryBean queryBean);
	
	/**
	 * 根据id查询车辆详细信息包括车辆的检验记录
	 *@Date 2017年7月10日
	 * @param id
	 * @return
	 */
	public CarInfoDetailBean findCarInfoDetailById(Integer id);
	
	/**
	 * 根据id查询车辆详记录，包含信息：车辆信息，车主信息，不包括车辆检修信息
	 *@Date 2017年7月10日
	 * @param id
	 * @return
	 */
	public CarInfoDto findCarInfoDtoById(@Param( value = "id" ) Integer id);
	
	/**
	 * 更新车辆信息
	 *@Date 2017年7月11日
	 * @param carInfoDto
	 */
	public void updateCarInfo(CarInfoDto carInfoDto);
	
	/**
	 * 查询车辆是否存在
	 *@Date 2017年7月17日
	 * @param carInfo
	 */
	public CarInfo findCarByCarNo(CarInfo carInfo);
	


}
