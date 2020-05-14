package com.demo.truck.common.util;

import java.util.List;

import com.demo.truck.entity.OrgInfo;

public class Util {

	final static String CAR_STATUS_0 = "待入";
	final static String CAR_STATUS_1 = "已入";
	final static String CAR_STATUS_2 = "报废";
	final static String VALIDE_TYPE_1="大验";
	final static String VALIDE_TYPE_2="二验";
	final static String STATSU_0="草稿";
	final static String STATSU_1="已提交";
	final static String YELLOW_BRAND="黄牌";
	final static String BLUE_BRAND="蓝牌";
	public static String getCarStatus(Integer status) {
		String carStatus = null;
		int key=0;
		if(status!=null){
			key=status;
		}
		switch (key) {
		case 0:
			carStatus = CAR_STATUS_0;
			break;
		case 1:
			carStatus = CAR_STATUS_1;
			break;
		case 2:
			carStatus = CAR_STATUS_2;
			break;
		default:
			break;
		}
		return carStatus;
	}
	public static String getValideType(Integer status) {
		String type = null;
		int key=0;
		if(status!=null){
			key=status;
		}
		switch (key) {
		case 1:
			type = VALIDE_TYPE_1;
			break;
		case 2:
			type = VALIDE_TYPE_2;
			break;
		default:
			break;
		}
		return type;
	}
	public static String getGuaranteeStatus(Integer status) {
		String type = null;
		int key=0;
		if(status!=null){
			key=status;
		}
		switch (key) {
		case 0:
			type = STATSU_0;
			break;
		case 1:
			type = STATSU_1;
			break;
		default:
			break;
		}
		return type;
	}
	public static String getCarCardColor(Integer status) {
		String type = null;
		int key=0;
		if(status!=null){
			key=status;
		}
		switch (key) {
		case 1:
			type = YELLOW_BRAND;
			break;
		case 2:
			type = BLUE_BRAND;
			break;
		default:
			break;
		}
		return type;
	}
}
