package com.naydog.common.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.runtime.InvokerHelper;

import com.naydog.common.encrypt.AESCrypto;

import groovy.lang.GroovyClassLoader;

/**
 * 工具类，用来将各种文件加载为Java对象
 * @author naydog
 *
 */
public class LoaderUtils {

	/**
	 * 加载groovy文件
	 * @param path groovy文件路径
	 * @return 加载的对象
	 */
	public static Object loadGroovy(String path) {
		return loadGroovy(path, null);
	}

	/**
	 * 加载groovy文件，文件内容需要解密
	 * @param path groovy文件路径
	 * @param symKey aes密钥
	 * @return 加载的对象
	 */
	@SuppressWarnings("rawtypes")
	public static Object loadGroovy(String path, String symKey) {
		Object obj = null;
		ClassLoader cl = new InvokerHelper().getClass().getClassLoader();
		GroovyClassLoader gcl = new GroovyClassLoader(cl);
		try {
			byte[] script = FileUtils.readFileToByteArray(new File(path));
			if (symKey != null) {
				AESCrypto aesCrypto = new AESCrypto(symKey);
				script = aesCrypto.decrypt(script);
			}
		
			Class gClazz = gcl.parseClass(new String(script));
			obj = gClazz.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				gcl.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return obj;
	}
	
}
