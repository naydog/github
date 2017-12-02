package com.naydog.common.utils;

/**
 * 工具类，与数字有关的方法，包括数字与字符串转换
 * @author naydog
 *
 */
public class NumberUtils {
	
	/**
	 * 去除浮点数尾部的0 以方便打印时控制字符数
	 * @param aDouble 需要转换的浮点数
	 * @return 转换后的字符串
	 */
	public static String compactFormat(double aDouble) {
		return ("" + aDouble).replaceAll("\\.0$", "");
	}
}
