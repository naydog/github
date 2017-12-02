package com.naydog.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类，各种数据类型表示的时间及其计算
 * @author naydog
 *
 */
public class TimeUtils {
	
	/** 字符串的时间格式 */
	private final static DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	/** +8时区和GMT的毫秒差 */
	public final static long TIMEZONE_OFFSET = 28800000;
	
	/**
	 * 长整形的时间值在一天内的时间
	 * @param timemillis 毫秒数时间
	 * @return 日内毫秒数时间
	 */
	public static int toMillisOfDay(long timemillis) {
		return (int) ((timemillis + TIMEZONE_OFFSET) % DateUtils.TIME_DURATION_1_DAY);
	}
	
	/**
	 * 获得时间字符串和整型毫秒数所表示的日内毫秒数时间
	 * @param hhmmss 时间字符串，格式HH:mm:ss
	 * @param millis 毫秒数
	 * @return 日内毫秒数时间
	 */
	public static int hhmmssToMillis(String hhmmss, int millis) {
		if ('-' == hhmmss.charAt(0)) {
			return Integer.MAX_VALUE;
		}
		try {
			Date date = TIME_FORMAT.parse(hhmmss);
			return toMillisOfDay(date.getTime() + millis);
		} catch (ParseException e) {
			e.printStackTrace();
			return Integer.MAX_VALUE;
		}
	}
	
	/**
	 * 获得时间字符串所表示的日内毫秒数时间
	 * @param hhmmss 时间字符串，格式HH:mm:ss
	 * @return 日内毫秒数时间
	 */
	public static int hhmmssToMillis(String hhmmss) {
		return hhmmssToMillis(hhmmss, 0);
	}

	/**
	 * 给定时间与系统时间差
	 * @param hhmmss 时间字符串，格式HH:mm:ss
	 * @param millis 日内毫秒数时间
	 * @return 毫秒数时间差
	 */
	public static int getMillisOffset(String hhmmss, int millis) {
		return currentMillisOfDay() - hhmmssToMillis(hhmmss, millis);
	}

	/**
	 * 给定时间与系统时间差
	 * @param hhmmss 时间字符串，格式HH:mm:ss
	 * @return 毫秒数时间差
	 */
	public static int getMillisOffset(String hhmmss) {
		return getMillisOffset(hhmmss, 0);
	}
	
	/**
	 * 获得当前系统时间的日内毫秒数时间
	 * @return 日内毫秒数时间
	 */
	public static int currentMillisOfDay() {
		return toMillisOfDay(System.currentTimeMillis());
	}
	
	/**
	 * 毫秒表示的时间
	 * @param millis 毫秒表示的时间，不包括日期，最大为23:59:59.999
	 * @return
	 */
	public static int millisToHHmmss(int millis) {
		int tmp = millis / 1000;
		int sec = tmp % 60;
		tmp = tmp / 60;
		int min = tmp % 60;
		int hr = tmp / 60;
		return hr * 10000 + min * 100 + sec;
	}
}
