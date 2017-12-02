package com.naydog.common.encrypt;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.naydog.common.utils.EncodeUtils;

/**
 * AES对称加密算法
 * @author naydog
 *
 */
public class AESCrypto extends Crypto {

	/** 默认对称密钥字符串 */
	private String aesKey = "18cacceb8eb59267bcbbbecbf785e2d3";
	
	/** 默认IV字符串 */
	private String aesIv = "0123456789abcdef";

	/** 对称密钥 */
	private SecretKeySpec keySpec;

	/**
	 * 构造函数
	 * 使用默认密钥
	 */
	public AESCrypto() {
		init();
	}

	/**
	 * 构造函数
	 * @param aesKey 对称密钥字符串
	 */
	public AESCrypto(String aesKey) {
		this.aesKey = aesKey;
		init();
	}
	
	/**
	 * 构造函数
	 * @param aesKey 对称密钥字符串
	 * @param aesIv IV字符串
	 */
	public AESCrypto(String aesKey, String aesIv) {
		this.aesKey = aesKey;
		this.aesIv = aesIv;
		init();
	}

	/**
	 * 初始化方法，根据密钥生成加密类和解密类
	 */
	private void init() {
		try {
			keySpec = new SecretKeySpec(EncodeUtils.hexToByte(aesKey), "AES");
			IvParameterSpec iv = new IvParameterSpec(aesIv.getBytes());

			encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

			decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		AESCrypto cryptor = new AESCrypto();
		String a = cryptor.encryptHex("abcdefg1234567");
		System.out.println(a);
		System.out.println(cryptor.decryptHex(a));

		String str = cryptor.encryptBase64("abcdefg1234567");
		System.out.println(str);
		System.out.println(cryptor.decryptBase64(str));
		
		// diffie-hellman		
		BigInteger p = new BigInteger("1003");
		BigInteger g = new BigInteger("1234567890");
		
		BigInteger A = g.pow(67865).mod(p);
		BigInteger B = g.pow(43213).mod(p);
		
		System.out.println(A);
		System.out.println(B);
		
		System.out.println(B.pow(67865).mod(p));
		System.out.println(A.pow(43213).mod(p));
	}

}

