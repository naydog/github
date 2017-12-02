package com.naydog.common.encrypt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.naydog.common.utils.EncodeUtils;

/**
 * RSA对称加密算法
 * @author naydog
 *
 */
public class RSACrypto extends Crypto {

	/** 默认公有密钥字符串 */
	private String base64PubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMjeWzN7m6B3RMZjMQNfEcb28EIjDT/z6Go4Alr/ahOCvzT8NLR6uCs5BY6DxAKOjfr3OtpsYwdHImVL+/80ZjisqzR5SPUglx3LLxkx0gAdjBLuSFKivk5tuWjjs0hQ/QTfkYhqPEfVuS1abb2O+Gy5Oz9W19cIAgws8ACVFGmQIDAQAB";
	
	/** 默认私有密钥字符串 */
	private String base64PriKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIyN5bM3uboHdExmMxA18RxvbwQiMNP/PoajgCWv9qE4K/NPw0tHq4KzkFjoPEAo6N+vc62mxjB0ciZUv7/zRmOKyrNHlI9SCXHcsvGTHSAB2MEu5IUqK+Tm25aOOzSFD9BN+RiGo8R9W5LVptvY74bLk7P1bX1wgCDCzwAJUUaZAgMBAAECgYABDZMZ+6Hllea2J9HcHjtXNjKDumnZPMFQKutr3NyhKpWRthhFeRz81rGPKPZCmMvd2SeptmUnucedJ+dj3cO6OkU6yyRAf7Cwx40PUH8QVne+qAINydiToAlPMjVt2eVXDrSI10AaEITE7VAHOZeUvISuFb2+ACApOE0AChG6DQJBAO/UhzfWTBYY1LhU5fo+1u4XZ1Oee/n3pIBV1KZOLScc0n8VSz5PCwPEkPSCBjc+Tc29p9q4/k5sFFW+PpFMw9sCQQCWB92pAbVylitarNg2IRv1/knSj6hDThM943xKqb0pMIoJf2lJ7M4BwgzJLvteiOeajk2URyP1rSfRk/eoG2ObAkEAi6jjpSxx2lxTY5Rs6DE30CZo0VT1NTd04BcbYk0cFi6+laq8ymgVLfiFwR0GqZzqiejrmn8n+FsP7G6A2PQvCQJAMwnz/2XEa3nov80jNK7oyzO5u3cBn/v0kc5M3z2FS3jmzLpagDqeDn0MSnjALPXYDH0yqSnWcuJ4NFTpIHhI1wJBAKFZgmxOPlW+rH/uiZk1C0uqMa/Wa8TRlEYSCROaYu3tTCR/oMzNmrLWlAx32ggriWDsWH7OuPsxsQUyt365qNw=";

	/** 公有密钥 */
	private PublicKey pubKey;
	
	/** 私有密钥 */
	private PrivateKey priKey;

	/**
	 * 构造函数
	 * 使用默认密钥
	 */
	public RSACrypto() {
		init();
	}

	/**
	 * 构造函数
	 * @param pubKeyString 公有密钥字符串
	 * @param priKeyString 私有密钥字符串
	 */
	public RSACrypto(String pubKeyString, String priKeyString) {
		this.base64PubKey = priKeyString;
		this.base64PriKey = priKeyString;
		init();
	}

	/**
	 * 获得公有密钥字符串
	 * @return 公有密钥字符串
	 */
	public String getPubKey() {
		return base64PubKey;
	}
	
	/**
	 * 初始化方法，根据密钥生成加密类和解密类
	 */
	private void init() {
		try {
			getPublicKey();
			getPrivateKey();
			
			encryptCipher = Cipher.getInstance("RSA");
			encryptCipher.init(Cipher.ENCRYPT_MODE, pubKey);

			decryptCipher = Cipher.getInstance("RSA");
			decryptCipher.init(Cipher.DECRYPT_MODE, priKey);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 生成公有密钥类
	 * @throws Exception
	 */
	private void getPublicKey() throws Exception {
		byte[] keyBytes = EncodeUtils.base64ToByte(base64PubKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		pubKey = keyFactory.generatePublic(keySpec);
	}
	
	/**
	 * 生成私有密钥类
	 * @throws Exception
	 */
	private void getPrivateKey() throws Exception {
		byte[] keyBytes = EncodeUtils.base64ToByte(base64PriKey);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		priKey = keyFactory.generatePrivate(keySpec);
	}
	
	public static void main(String[] args) throws Exception {
//		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
//		KeyPair pair = gen.generateKeyPair();
//		PrivateKey priKey = pair.getPrivate();
//		PublicKey pubKey = pair.getPublic();
//		
//		Encoder base64Encoder = Base64.getEncoder();
//		Decoder base64Decoder = Base64.getDecoder();
//		
//		System.out.println(priKey.getFormat());
//		System.out.println(priKey.getAlgorithm());
//		System.out.println(base64Encoder.encodeToString(priKey.getEncoded()));
//		
//		System.out.println(pubKey.getFormat());
//		System.out.println(pubKey.getAlgorithm());
//		System.out.println(base64Encoder.encodeToString(pubKey.getEncoded()));
		
		RSACrypto cryptor = new RSACrypto();
//		System.out.println(HexUtils.byte2Hex( cryptor.encrypt("abcdefg1234567".getBytes())));
//		System.out.println(a);
//		System.out.println(cryptor.decrypt(a));

		String str1 = "abcdefg1234567";
		String str = cryptor.encryptBase64(str1);
		System.out.println(str);
		System.out.println(cryptor.decryptBase64(str));
		
		String str2 = cryptor.encryptHex(str1);
		System.out.println(str2);
		System.out.println(cryptor.decryptHex(str2));
		
		
	}

}
