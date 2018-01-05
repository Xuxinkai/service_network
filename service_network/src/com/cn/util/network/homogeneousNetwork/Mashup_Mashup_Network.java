package com.cn.util.network.homogeneousNetwork;

import com.cn.bean.WebMashup;
import com.cn.util.network.computingFormula.NetworkComputingFormula;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午1:00:30
 * @description Mashup的同构网络 mashup被api和tag修饰 有三种mashup的同构网络图
 */
public class Mashup_Mashup_Network {

	/**
	 * 计算mashup之间的相似度 在数据库中tags是以","分割，apis是以"+"分割。
	 * 
	 * @param m1
	 *            第一个mashup的参数
	 * @param m2
	 *            第二个mashup的参数
	 * @param a
	 *            α的值 api的权值
	 * @param b
	 *            β的值 tag的权值
	 * @return 返回一个分数
	 */

	public static double CalculateMashupSimilarity(WebMashup m1, WebMashup m2,
			double a, double b) {
		// 获得第一个mashup的apis和tags 除开空值的情况
		String[] m1apis = null;
		if (m1.getMashup_apis() != "" && m1.getMashup_apis() != null) {
			m1apis = m1.getMashup_apis().split("\\+");
		} else {
			m1apis = new String[] {};
		}

		String[] m1tags = null;
		if (m1.getMashup_tags() != "" && m1.getMashup_tags() != null) {
			m1tags = m1.getMashup_tags().split(",");
		} else {
			m1tags = new String[] {};
		}
		// 获得第二个mashup的apis和tags 除开空值的情况
		String[] m2apis = null;
		if (m2.getMashup_apis() != "" && m2.getMashup_apis() != null) {
			m2apis = m2.getMashup_apis().split("\\+");
		} else {
			m2apis = new String[] {};
		}

		String[] m2tags = null;
		if (m2.getMashup_tags() != "" && m2.getMashup_tags() != null) {
			m2tags = m2.getMashup_tags().split(",");
		} else {
			m2tags = new String[] {};
		}
		// 计算相似度
		double score = NetworkComputingFormula.getWeight(m1apis, m2apis,
				m1tags, m2tags, a, b);
		return score;
	}
}
