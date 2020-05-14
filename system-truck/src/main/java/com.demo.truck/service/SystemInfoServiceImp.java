
package com.demo.truck.service;

import java.util.ArrayList;
import java.util.List;

import com.demo.truck.dao.OrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.truck.common.MyNode;
import com.demo.truck.entity.OrgInfo;
import com.demo.truck.exception.ServiceException;
import com.demo.truck.remote.SystemService;

@Service
@Transactional
public class SystemInfoServiceImp implements SystemService {
	@Autowired
	private OrgDao orgDao;

	@Override
	public boolean addOrg(OrgInfo orgInfo) throws ServiceException {
		if (orgInfo != null) {
			orgDao.saveOrg(orgInfo);
			return true;
		}
		throw new ServiceException("保存失败!");
	}

	@Override
	public boolean deleteOrg(OrgInfo orgInfo) throws ServiceException {
		if (orgInfo != null) {
			// 删除所有子节点
			orgDao.deleteOrgByPid(orgInfo.getId());
			// 删除所有自身节点
			orgDao.deleteOrgById(orgInfo.getId());
			return true;
		}
		throw new ServiceException("删除失败!");

	}

	@Override
	public List<OrgInfo> findToprgs() {
		List<OrgInfo> orgTopsList = orgDao.findChildsBy(null);
		return orgTopsList;
	}

	@Override
	public MyNode<OrgInfo> findOrgTree() {

		OrgInfo element = new OrgInfo();
		element.setOrgName("组织树");
		MyNode<OrgInfo> node = new MyNode<OrgInfo>(element);
		// 查询所有顶级节点
		List<OrgInfo> orgTopsList = orgDao.findChildsBy(null);
		List<MyNode<OrgInfo>> childList = new ArrayList<>();
		for (OrgInfo orgInfo : orgTopsList) {
			MyNode<OrgInfo> child = new MyNode<OrgInfo>(orgInfo);
			childList.add(child);
			createTreeByRoot(child);
		}
		node.setChildList(childList);

		return node;
	}

	private MyNode<OrgInfo> createTreeByRoot(MyNode<OrgInfo> root) {
		OrgInfo orgInfo = root.getElement();
		// 查看根节点是否有子节点
		List<OrgInfo> childList = orgDao.findChildsBy(orgInfo.getId());
		if (childList == null || childList.size() == 0) {
			return root;
		}
		List<MyNode<OrgInfo>> nodeList = new ArrayList<>();
		for (OrgInfo child : childList) {
			MyNode<OrgInfo> node = new MyNode<OrgInfo>(child);
			nodeList.add(node);
			createTreeByRoot(node);
		}
		root.setChildList(nodeList);
		return root;

	}
}
