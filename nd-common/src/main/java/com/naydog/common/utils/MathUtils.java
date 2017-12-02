package com.naydog.common.utils;

/**
 * 工具类，各种数学计算
 * @author naydog
 *
 */
public class MathUtils {
	
	/**
	 * 四舍五入到几位小数
	 * @param aDouble 要四舍五入的数
	 * @param digit 保留小数位数
	 * @return 四舍五入后的值
	 */
	public static double round(double aDouble, int digit) {
		double pow = Math.pow(10, digit);
		return (double) Math.round(aDouble * pow) / pow;
	}

}
