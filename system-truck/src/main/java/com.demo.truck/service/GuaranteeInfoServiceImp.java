
package com.demo.truck.service;

import com.demo.truck.dao.CarGuaranteeRefDao;
import com.demo.truck.dao.CarInfoDao;
import com.demo.truck.dao.CarOwnerRefDao;
import com.demo.truck.dao.GuaranteeInfoDao;
import com.demo.truck.dao.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.truck.Dto.CarInfoDto;
import com.demo.truck.Dto.GuaranteeInfoDto;
import com.demo.truck.bean.GuaranteeQueryBean;
import com.demo.truck.entity.CarInfo;
import com.demo.truck.entity.Owner;
import com.demo.truck.remote.GuaranteeInfoService;

/**
 * 保单管理服务类
 * 
 * @author demo
 * @date 2017年7月7日
 */
@Service
@Transactional
public class GuaranteeInfoServiceImp implements GuaranteeInfoService {

	@Autowired
	private GuaranteeInfoDao guaranteeInfoDao;
	@Autowired
	private CarGuaranteeRefDao carGuaranteeRefDao;
	@Autowired
	private CarInfoDao carInfoDao;
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private CarOwnerRefDao carOwnerRefDao;

	/**
	 * 新增保存
	 * 
	 * @Date 2017年7月7日
	 * @param guaranteeInfoDto
	 * @return
	 */
	@Override
	public boolean saveGuaranteeInfo(GuaranteeInfoDto guaranteeInfoDto) {
		if (guaranteeInfoDto != null) {
			try {
				if(guaranteeInfoDto.getGuaranteeId()==null){
					//保单不存在，新增一条记录
					CarInfo carInfo=guaranteeInfoDto.getCarInfo();
					if(carInfo!=null&&carInfo.getId()==null){
						//车辆不存在
						Owner owner=guaranteeInfoDto.getOwner();
						if(owner!=null&&owner.getId()==null){
							//如果用户不存在
							ownerDao.saveOwner(owner);
						}
						CarInfoDto carInfoDto = new CarInfoDto();
						carInfoDto .setOwner(owner);
						carInfo.setStatus(1);
						carInfoDto.setCarInfo(carInfo);
						//车辆不在记录中
						carInfoDao.saveCarInfo(carInfo);
						carOwnerRefDao.saveCarOwnerRef(carInfoDto);
						
					}
					guaranteeInfoDao.savaGuaranteeInfo(guaranteeInfoDto);
					carGuaranteeRefDao.saveCarGuaranteeRef(guaranteeInfoDto);
					
				}else{
					//修改保单记录
					guaranteeInfoDao.updateGuaranteeInfo(guaranteeInfoDto);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		return false;
	}

	@Override
	public GuaranteeQueryBean findGuaranteeList(GuaranteeQueryBean queryBean) {
		queryBean.setResultList(guaranteeInfoDao.findGuaranteeList(queryBean));
		queryBean.setTotalRows(guaranteeInfoDao.findGuaranteeListCount(queryBean));
		return queryBean;
	}

	@Override
	public GuaranteeInfoDto findGuaranteeDetailById(Integer guaranteeId) {
		GuaranteeInfoDto detail = guaranteeInfoDao.findGuaranteeDetailById(guaranteeId);
		return detail;
	}

	@Override
	public boolean submitGuaranteeInfo(GuaranteeInfoDto guaranteeInfoDto) {
		if(guaranteeInfoDto!=null){
			guaranteeInfoDto.setStatus(1);
			return saveGuaranteeInfo(guaranteeInfoDto);
		}
		return false;
	}

}
