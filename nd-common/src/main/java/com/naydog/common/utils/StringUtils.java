package com.naydog.common.utils;

/**
 * 工具类，字符串转换有关的操作
 * @author naydog
 *
 */
public class StringUtils {

	/**
	 * 将有多行内容的字符串转为字符串数组
	 * @param multipleLines 多行内容的字符串
	 * @return 字符串数组
	 */
	public static String[] linesToArray(String multipleLines) {
		return multipleLines.split("\n");
	}
}
