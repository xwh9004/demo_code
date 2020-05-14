package com.demo.truck.service;


import com.demo.truck.dao.OwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.truck.bean.OwnerQueryBean;
import com.demo.truck.entity.Owner;
import com.demo.truck.remote.OwnerService;

/**
 * 
 * @author demo
 * @date 2017年7月6日
 */
@Service
@Transactional
public class OwnerServiceImp implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	/**
	 * 保存车主信息
	 * 
	 * @Date 2017年7月6日
	 */
	public boolean saveOwner(Owner owner) {
		try {
			if (owner != null) {
				if(owner.getId()==null){
					ownerDao.saveOwner(owner);
				}else{
					ownerDao.updateOwner(owner);
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据条件查询车主信息
	 * @Date 2017年7月6日
	 * @param queryBean
	 * @return
	 */
	@Override
	public OwnerQueryBean findOwnersList(OwnerQueryBean queryBean) {
		queryBean.setResultList(ownerDao.findOwnerList(queryBean));
		queryBean.setTotalRows(ownerDao.findOwnerListCount(queryBean));
		return queryBean;
	}

	@Override
	public Owner findOwnerById(Integer id) {
		return ownerDao.findOwnerById(id);
	}
}
