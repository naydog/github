package com.naydog.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import net.sf.json.JSONObject;

/**
 * 工具类，用于生成web请求时，返回规范的json格式数据
 * @author naydog
 *
 */
public class WebResponseUtils {

	private static final String JSON_PROP_STATUS = "status";
	private static final String JSON_PROP_DATA = "data";
	private static final String JSON_PROP_LIST = "list";
	
	/**
	 * 返回一个对象
	 * @param object 要返回的对象
	 * @return 将对象转换后的json字符串
	 */
	public static String returnData(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(JSON_PROP_STATUS, null != object);
		map.put(JSON_PROP_DATA, object);
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 返回一个数组
	 * @param array 要返回的数组
	 * @return 将数组转换后的json字符串
	 */
	public static String returnList(Collection<Object> array) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(JSON_PROP_STATUS, !CollectionUtils.isEmpty(array));
		map.put(JSON_PROP_LIST, array);
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 返回布尔值
	 * @param bool 要返回的数组
	 * @return 将布尔转换后的json字符串
	 */
	public static String returnBoolean(boolean bool) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(JSON_PROP_STATUS, bool);
		return JSONObject.fromObject(map).toString();
	}
}
