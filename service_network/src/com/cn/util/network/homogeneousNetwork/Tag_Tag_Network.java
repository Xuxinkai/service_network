package com.cn.util.network.homogeneousNetwork;

import com.cn.bean.WebTag;
import com.cn.util.network.computingFormula.NetworkComputingFormula;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午12:55:50
 * @description tag的同构网络，tag被api和mashup修饰，有三种tag的同构网络图 网络计算公式
 */
public class Tag_Tag_Network {

	/**
	 * 计算两个tag之间的相似度
	 * 
	 * @param t1
	 *            第一个tag
	 * @param t2
	 *            第二个tag
	 * @param a
	 *            α的值 api的权值
	 * @param b
	 *            β的值 mashup的权值
	 * @return
	 */
	public static double CalculateTagSimilarity(WebTag t1, WebTag t2, double a,
			double b) {
		// 获得第一个tag的api和mashup
		String[] t1apis = getApis(t1);
		String[] t1mashups = getMashups(t1);

		// 获得第二个tag的api和mashup
		String[] t2pais = getApis(t2);
		String[] t2mashups = getMashups(t2);

		double score = NetworkComputingFormula.getWeight(t1apis, t2pais,
				t1mashups, t2mashups, a, b);
		return score;
	}

	/**
	 * 获得tag标记过的api,考虑空值情况
	 * 
	 * @param tag
	 * @return
	 */
	private static String[] getApis(WebTag tag) {
		String[] apis = new String[] {};
		if (tag.getTag_apis() != null && tag.getTag_apis() != "") {
			apis = tag.getTag_apis().split(",");
		}
		return apis;
	}

	/**
	 * 获得tag修饰的mashup，考虑空值情况
	 * 
	 * @param tag
	 * @return
	 */
	private static String[] getMashups(WebTag tag) {
		String[] mashups = new String[] {};
		if (tag.getTag_mashups() != null && tag.getTag_mashups() != "") {
			mashups = tag.getTag_mashups().split(",");
		}
		return mashups;
	}

}
