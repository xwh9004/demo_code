package com.demo.truck.service;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.bean.CarInfoDetailBean;
import com.demo.truck.bean.CarInfoQueryBean;
import com.demo.truck.dao.CarCompesationRecordDao;
import com.demo.truck.dao.CarInfoDao;
import com.demo.truck.dao.CarOwnerRefDao;
import com.demo.truck.dao.CarValidateRecordDao;
import com.demo.truck.dao.OwnerDao;
import com.demo.truck.entity.CarCompesationRecord;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.CarValidateRecord;
import com.demo.truck.entity.Owner;
import com.demo.truck.exception.ServiceException;
import com.demo.truck.remote.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarInfoServiceImp implements CarInfoService {

	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private CarInfoDao carInfoDao;
	@Autowired
	private CarOwnerRefDao carOwnerRefDao;
	@Autowired
	private CarCompesationRecordDao carCompesationRecordDao;
	@Autowired
	private CarValidateRecordDao carValidateRecordDao;

	/**
	 * 新增车辆
	 * 
	 * @Date 2017年7月7日
	 * @param carInfoDto
	 * @return
	 * @throws ServiceException
	 */
	public void saveCarInfo(CarInfoDto carInfoDto) throws ServiceException {
		if (carInfoDto == null) {
			throw new ServiceException("车辆信息不能为空!");
		}
		// 保存车辆信息
		// 车辆信息记录原先不存在,新增一条记录
		Owner owner = carInfoDto.getOwner();
		CarInfo carInfo = carInfoDto.getCarInfo();
		if (carInfo != null) {
			if (carInfoDao.findCarByCarNo(carInfo) != null) {
				throw new ServiceException("车辆已存在!");
			}
			if (owner != null) {
				if (owner.getId() == null || "".equals(owner.getId())) {
					// 车主不存在,新增车主
					ownerDao.saveOwner(carInfoDto.getOwner());
				}
			}
			if (carInfo.getId() == null || "".equals(carInfo.getId())) {
				// 车辆不存在,新增车辆
				carInfoDao.saveCarInfo(carInfoDto.getCarInfo());
				carOwnerRefDao.saveCarOwnerRef(carInfoDto);
			} else {
				// 车辆信息记录已存在，修改记录
				carInfoDao.updateCarInfo(carInfoDto);
				carOwnerRefDao.updateCarOwnerRef(carInfoDto);
			}
		} else {
			throw new ServiceException("车辆信息不能为空!");
		}

	}

	/**
	 * 查询车辆列表信息
	 * 
	 * @Date 2017年7月7日
	 * @param queryBean
	 * @return
	 */
	@Override
	public CarInfoQueryBean findCarInfoList(CarInfoQueryBean queryBean) {
		queryBean.setResultList(carInfoDao.findCarInfoList(queryBean));
		queryBean.setTotalRows(carInfoDao.findCarInfoListCount(queryBean));
		return queryBean;
	}

	@Override
	public CarInfoDetailBean findCarInfoDetailById(Integer id) {
		return carInfoDao.findCarInfoDetailById(id);
	}

	@Override
	public CarInfoDto findCarInfoDtoById(Integer id) {

		return carInfoDao.findCarInfoDtoById(id);
	}

	@Override
	public void submitCarInfo(CarInfoDto carInfoDto) throws ServiceException {
		if (carInfoDto == null) {
			throw new ServiceException("车辆信息不能为空!");
		}
		carInfoDto.getCarInfo().setStatus(1);
		saveCarInfo(carInfoDto);
	}

	@Override
	public void addCarValidateRecordList(List<CarValidateRecord> recordList, Integer carId) throws ServiceException {
			if (recordList != null && recordList.size() != 0) {
				carValidateRecordDao.addRecords(recordList, carId);
			}else{
				throw new ServiceException("验车记录不能为空!");
			}
	}

	@Override
	public void addCarCompesationRecordList(List<CarCompesationRecord> recordList, Integer carId) throws ServiceException{
			if (recordList != null && recordList.size() != 0) {
				carCompesationRecordDao.addCompesationRecords(recordList, carId);
			}else{
				throw new ServiceException("出险记录不能为空!");
			}
	}

}
