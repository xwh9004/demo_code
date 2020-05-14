package com.demo.truck.common.util;

import java.util.List;

import com.demo.truck.entity.OrgInfo;

public class OrgUtil {
	public static OrgInfo getOrgInfoByCode(String code, List<OrgInfo> orgList) {
		OrgInfo orgInfo = null;
		if(code==null){
			return null;
		}
		for (OrgInfo org : orgList) {
			if (org.toString().equals(code)) {
				orgInfo = org;
				break;
			}
		}
		return orgInfo;
	}
}
