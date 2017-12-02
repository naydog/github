package com.naydog.common.utils;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 工具类，编码解码用
 * @author naydog
 * 
 */
public class EncodeUtils {

	/** 编码实例 */
	private static Encoder base64Encoder = Base64.getEncoder();
	
	/** 解码实例 */
	private static Decoder base64Decoder = Base64.getDecoder();
	
	/**
	 * 将16进制字符串转为byte数组
	 * @param hex 16进制字符串
	 * @return byte数组
	 */
	public static byte[] hexToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * 字符取byte
	 * @param c 字符
	 * @return 对应byte值
	 */
	private static byte toByte(char c) {
		byte b = (byte) "0123456789abcdef".indexOf(c);
		return b;
	}
	
	/**
	 * byte数组转成16进制字符串
	 * @param bytes byte数组
	 * @return 16进制字符串
	 */
	public static String byteToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(String.format("%02x", bytes[i] & 0xff));
		}
		return sb.toString();
	}

	/**
	 * 将base64字符串转为byte数组
	 * @param str base64字符串
	 * @return byte数组
	 */
	public static byte[] base64ToByte(String str) {
		return base64Decoder.decode(str);
	}
	
	/**
	 * byte数组转成base64字符串
	 * @param bytes byte数组
	 * @return base64字符串
	 */
	public static String byteToBase64(byte[] bytes) {
		return base64Encoder.encodeToString(bytes);
	}
	
}
