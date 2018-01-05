package com.cn.util;

import java.util.List;

import net.sf.json.JSONObject;

import com.cn.bean.ApiNet;
import com.cn.bean.ApiRelation;
import com.cn.dao.ApiNetDao;
import com.cn.dao.ApiRelationDao;
import com.cn.dao.impl.ApiNetDaoImpl;
import com.cn.dao.impl.ApiRelationDaoImpl;

public class Test {

	public static void main(String[] args) {
		String path = Test.class.getResource("/").getPath();
		String filePath = path.substring(0, path.lastIndexOf("WEB-INF"))+"search/analyzeData";
		System.out.println(filePath);
	}

	/**
	 * 将java对象List集合转换成json字符串
	 * 
	 * @param beans
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String beanListToJson(List beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("{\"nodes\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
		}
		rest.append("]");
		return rest.toString();
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {
		JSONObject json = JSONObject.fromObject(bean);
		return json.toString();

	}
}
