package com.naydog.common.encrypt;


import javax.crypto.Cipher;

import com.naydog.common.utils.EncodeUtils;

/**
 * 抽象加密类，适用对称加密和非对称加密
 * 公共方法抽象到这里，减少代码量
 * @author naydog
 *
 */
public abstract class Crypto {
	
	/** 用于加密的类 */
	protected Cipher encryptCipher;
	
	/** 用于解密的类 */
	protected Cipher decryptCipher;
	
	/**
	 * byte数组加密后返回byte数组
	 * @param rawBytes 原始byte数组
	 * @return 加密后数组
	 */
	public byte[] encrypt(byte[] rawBytes) {
		try {
			return encryptCipher.doFinal(rawBytes);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * byte数组解密
	 * @param encryptedBytes 加密过的数组
	 * @return 解密后数组
	 */
	public byte[] decrypt(byte[] encryptedBytes) {
		try {
			return decryptCipher.doFinal(encryptedBytes);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 原始字符串加密为16进制字符串
	 * @param rawString 原始字符串
	 * @return 加密后的16进制字符串
	 */
	public String encryptHex(String rawString) {
		byte[] rawBytes = rawString.getBytes();
		byte[] encryptedBytes = encrypt(rawBytes);
		return EncodeUtils.byteToHex(encryptedBytes);
	}

	/**
	 * 将加密过的16进制字符串解密
	 * @param encryptedHexString 加密过的16进制字符串
	 * @return 解密后的16进制字符串
	 */
	public String decryptHex(String encryptedHexString) {
		byte[] encryptedBytes = EncodeUtils.hexToByte(encryptedHexString);
		byte[] decryptedBytes = decrypt(encryptedBytes);
		return decryptedBytes != null ? new String(decryptedBytes) : null;
	}

	/**
	 * 原始字符串加密为base64字符串
	 * @param rawString 原始字符串
	 * @return 加密后的base64字符串
	 */
	public String encryptBase64(String rawString) {
		byte[] rawBytes = rawString.getBytes();
		byte[] encryptedBytes = encrypt(rawBytes);
		return EncodeUtils.byteToBase64(encryptedBytes);
	}

	/**
	 * 将加密过的base64字符串解密
	 * @param encryptedBase64String 加密过的base64字符串
	 * @return 解密后的base64字符串
	 */
	public String decryptBase64(String encryptedBase64String) {
		byte[] encryptedBytes = EncodeUtils.base64ToByte(encryptedBase64String);
		byte[] decryptedBytes = decrypt(encryptedBytes);
		return decryptedBytes != null ? new String(decryptedBytes) : null;
	}
}

