package com.cn.util.network.computingFormula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午1:05:27
 * @description 网络边的权值计算公式 网络节点的相似度计算公式<br>
 *              公式:W(Vi,Vj)=a(|para1(Vi) ∩ para1(Vj)| / |para1(Vi) ∪ para1(Vj)|)
 *              + b(|para2(Vi) ∩ para2(Vj)| / |para2(Vi) ∪ para2(Vj)|)
 */
public class NetworkComputingFormula {

	public static void main(String[] args) {
		String[] api1 = "messaging deadpool sms telephony ".split(" ");
		String[] api2 = "messaging deadpool sms telephony ".split(" ");

		String[] tag1 = {};
		String[] tag2 = {};

		String[] api_union = union(api1, api2);
		String[] tag_union = union(tag1, tag2);

		String[] api_insect = intersect(api1, api2);
		String[] tag_insect = intersect(tag1, tag2);

		System.out.println("api求交集的结果如下：");
		for (String str : api_insect) {
			System.out.println(str);
		}
		System.out.println("api求并集的结果如下：");
		for (String str : api_union) {
			System.out.println(str);
		}

		double api_score = (double) api_insect.length
				/ (double) api_union.length;
		System.out.println("两个api交集除以并集：" + api_score);

		System.out
				.println("---------------------可爱的分割线------------------------");
		// 测试insect
		System.out.println("tag求交集的结果如下：");
		for (String str : tag_insect) {
			System.out.println(str);
		}
		System.out.println("tag求并集的结果如下：");
		for (String str : tag_union) {
			System.out.println(str);
		}
		double tag_score = (double) tag_insect.length
				/ (double) tag_union.length;
		System.out.println("两个tag交集除以并集：" + tag_score);
		System.out
				.println("---------------------可爱的分割线------------------------");

		double score = 0.5 * api_score + 0.5 * tag_score;
		System.out.print("计算相似度：");

		System.out.println(getWeight(api1, api2, tag1, tag2, 0.6, 0.4));

	}

	/**
	 * 公式:W(Vi,Vj)=α(|para1(Vi) ∩ para1(Vj)| / |para1(Vi) ∪ para1(Vj)|) +
	 * β(|para2(Vi) ∩ para2(Vj)| / |para2(Vi) ∪ para2(Vj)|)
	 * 
	 * @param para1Vi
	 * @param para1Vj
	 * @param para2Vi
	 * @param para2Vj
	 * @param a
	 *            α值
	 * @param b
	 *            β值 这里 α+β=1，且都大于0
	 * 
	 * @return 返回一个相似度值
	 * @param para1
	 *            第一个参数集合
	 * @param para2
	 *            第二个参数集合
	 */
	public static double getWeight(String[] para1Vi, String[] para1Vj,
			String[] para2Vi, String[] para2Vj, double a, double b) {
		String[] para1_union = union(para1Vi, para1Vj);// 并集
		String[] para1_insect = intersect(para1Vi, para1Vj);// 交集

		String[] para2_union = union(para2Vi, para2Vj);
		String[] para2_insect = intersect(para2Vi, para2Vj);
		// 计算分数 这里要考虑并集为空的情况，也就是考虑分母为0的情况
		double para1_score = 0;
		if (para1_union.length > 0) {
			para1_score = (double) para1_insect.length
					/ (double) para1_union.length;
		}
		double para2_score = 0;
		if (para2_union.length > 0) {
			para2_score = (double) para2_insect.length
					/ (double) para2_union.length;
		}

		double score = a * para1_score + b * para2_score;
		String result = String.format("%.6f", score);
		return Double.parseDouble(result);
	}

	/**
	 * 求两个字符串数组的并集，利用set的元素唯一性
	 * 
	 * @param arr1
	 *            字符串数组1
	 * @param arr2
	 *            字符串数组2
	 * @return 两个字符串数组的并集
	 */
	private static String[] union(String[] arr1, String[] arr2) {
		Set<String> set = new HashSet<String>();
		for (String str : arr1) {
			set.add(str);
		}
		for (String str : arr2) {
			set.add(str);
		}
		String[] result = {};
		return set.toArray(result);
	}

	/**
	 * 求两个字符串数组的交集
	 * 
	 * @param arr1
	 *            字符串数组1
	 * @param arr2
	 *            字符串数组2
	 * @return 两个字符串数组的交集
	 */
	private static String[] intersect(String[] arr1, String[] arr2) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		List<String> list = new ArrayList<String>();
		// 通过Map来获取两个集合的相同元素
		for (String str : arr1) {
			if (!map.containsKey(str)) {
				map.put(str, Boolean.FALSE);
			}
		}
		for (String str : arr2) {
			if (map.containsKey(str)) {
				map.put(str, Boolean.TRUE);
			}
		}
		// 将Map中相同元素添加加list中
		for (Iterator<Entry<String, Boolean>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, Boolean> e = (Entry<String, Boolean>) it.next();
			if (e.getValue().equals(Boolean.TRUE)) {
				list.add(e.getKey());
			}
		}
		return list.toArray(new String[] {});
	}
}
