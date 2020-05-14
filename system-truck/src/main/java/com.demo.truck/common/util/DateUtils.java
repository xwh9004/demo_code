package com.demo.truck.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static SimpleDateFormat format = new SimpleDateFormat();
	public static String format(Date date,String pattern){
		String value=null;
		if(date!=null){
			format.applyPattern(pattern);
			value=format.format(date);
		}
		return value;
	}
	public static Date format(String pattern,String source){
		Date date;
		if(pattern!=null&&source!=null&&!"".equals(source)){
			format.applyPattern(pattern);
			try {
				return format.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
