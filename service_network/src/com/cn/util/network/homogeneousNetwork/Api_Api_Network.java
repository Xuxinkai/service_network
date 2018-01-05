package com.cn.util.network.homogeneousNetwork;

import java.util.ArrayList;
import java.util.List;

import com.cn.bean.WebApi;
import com.cn.bean.WebMashup;
import com.cn.dao.WebMashupDao;
import com.cn.dao.impl.WebMashupDaoImpl;
import com.cn.util.network.computingFormula.NetworkComputingFormula;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午12:37:46
 * @description Api的同构网络计算 Api有tag修饰，也有Mashup来调用，那么就有三种Api的同构网络。
 */
public class Api_Api_Network {

	/**
	 * 计算两个api之间的相似度
	 * 
	 * @param a1
	 *            第一个api
	 * @param a2
	 *            第二个api
	 * @param a
	 *            α的值 也就是tag的权值
	 * @param b
	 *            β的值 也就是mashup的权值
	 * @return
	 */
	public static double CalculateAPISimilarity(WebApi a1, WebApi a2, double a,
			double b) {// 计算api的相似度
		// 获得第一个api的tags和mashups
		String[] a1tags = getTags(a1);
		String[] a1mashups = getMashups(a1);
		// 获得第二个api的tags和mashups
		String[] a2tags = getTags(a2);
		String[] a2mashups = getMashups(a2);

		double score = NetworkComputingFormula.getWeight(a1tags, a2tags,
				a1mashups, a2mashups, a, b);
		return score;
	}

	/**
	 * 获得修饰api的tags,在数据库中 修饰api的tags是以空格 " "隔开的
	 * 
	 * @param api
	 * @return
	 */
	private static String[] getTags(WebApi api) {
		String[] tags = null;
		if (api.getApi_tags() != null && api.getApi_tags() != "") {
			tags = api.getApi_tags().trim().split(" ");
		} else {
			tags = new String[] {};
		}
		return tags;
	}

	/**
	 * 获得调用api的mashup,在数据库中mashups是以逗号","分割的
	 * 
	 * @param api
	 */
	private static String[] getMashups(WebApi api) {
		String[] mashups = new String[] {};
		
		if (api.getApi_mashups() != null && api.getApi_mashups() != "") {
			mashups = api.getApi_mashups().split(",");
		}
		return mashups;
	}
}
