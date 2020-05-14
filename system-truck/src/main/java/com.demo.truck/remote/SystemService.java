
package com.demo.truck.remote;
import java.util.List;

import com.demo.truck.common.MyNode;
/**
 * 系统服务类
 */
import com.demo.truck.entity.OrgInfo;
import com.demo.truck.exception.ServiceException;

public interface SystemService {
	public boolean addOrg(OrgInfo orgInfo) throws ServiceException;
	
	public boolean deleteOrg(OrgInfo orgInfo) throws ServiceException;
	
	public List<OrgInfo> findToprgs();
	
	public MyNode<OrgInfo> findOrgTree();

}
