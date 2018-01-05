package com.cn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:43:35
 * @description
 */
public class DateHelper {

	public static String getDateTimeForNumber() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	public static String getDateTimeFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public static String getDateTimeFormatDuan() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getYearFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(new Date());
	}
}
