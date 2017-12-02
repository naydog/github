package com.naydog.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类，各种数据类型表示的日期及其计算
 * 字符串日期格式为yyyyMMdd
 * 数字日期如： 20171123
 * @author naydog
 *
 */
public class DateUtils {
	
	/** 一天的毫秒数 */
	public final static long TIME_DURATION_1_DAY = 86400000;

	/** 日期格式 */
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 日期类型转字符串表示的日期
	 * @param date 日期类型
	 * @return 字符串表示的日期
	 */
	public static String format(Date date) {
		return DATE_FORMAT.format(date);
	}

	/**
	 * 字符串表示的日期转成日期类型
	 * @param date 字符串表示的日期
	 * @return 日期类型
	 */
	public static Date parse(String date) {
		try {
			return DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 数字表示的日期转成日期类型
	 * @param date 数字表示的日期
	 * @return 日期类型
	 */
	public static Date parse(int date) {
		return parse("" + date);
	}

	/**
	 * 两个日期相差的天数
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 相差天数
	 */
	public static int dayDiff(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / TIME_DURATION_1_DAY);
	}

	/**
	 * 两个日期相差的天数
	 * @param date1 字符串日期1
	 * @param date2 字符串日期2
	 * @return 相差天数
	 */
	public static int dayDiff(String date1, String date2) {
		return dayDiff(parse(date1), parse(date2));
	}
	
	/**
	 * 两个日期相差的天数
	 * @param date1 数字表示的日期1
	 * @param date2 数字表示的日期2
	 * @return 相差天数
	 */
	public static int dayDiff(int date1, int date2) {
		return dayDiff(parse(date1), parse(date2));
	}

}
