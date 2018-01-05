package com.cn.util.network.analyze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cn.bean.WebMashup;
import com.cn.bean.WebMashupRelation;
import com.cn.dao.WebMashupDao;
import com.cn.dao.WebMashupRelationDao;
import com.cn.dao.impl.WebMashupDaoImpl;
import com.cn.dao.impl.WebMashupRelationDaoImpl;
import com.cn.util.network.homogeneousNetwork.Mashup_Mashup_Network;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午8:53:55
 * @description 1.计算出所有mashup的相似度并写入文件<br/>
 *              这里有三种情况：a=1,b=0(只考虑api)或a=0,b=1(只考虑tag)或0《a《1,0《b《1,a+b=1(
 *              api和tag都考虑);<br>
 *              2.Mashup包含的API数量分布及包含最多API的前30个Mashup<br>
 */
public class MashupSimilarityAnalyze {
	private static String mashupScorePath = MashupSimilarityAnalyze.class
			.getResource("/").getPath();

	public static void main(String[] args) {
		// 获取mashup的集合
		// WebMashupDao mdao = new WebMashupDaoImpl();
		List<WebMashup> listMashups = getFirstNMashups(1000);
		// calcMashupSimilarity(listMashups);
		for (int i = 0; i < listMashups.size(); i++) {
			WebMashup m = listMashups.get(i);
			System.out.println(i + "::" + m.getMashup_name() + "::"
					+ m.getMashup_apis().split("\\+").length);
		}

	}
	/**
	 * 获取mashup的总数
	 * @return
	 */
	public static int getMashupCount(){
		return new WebMashupDaoImpl().getMashupByKeyWord("").size();
	}
	/**
	 * 获得所有的mashup
	 * @return
	 */
	public static List<WebMashup> getAllMashups(){
		return new WebMashupDaoImpl().getMashupByKeyWord("");
	}

	/**
	 * Mashup包含的API数量分布 这里我估计是返回一个使用了api的mashup集合 这里可以显示在html页面的 api是以+分割的<br>
	 * 这里可以排个序，使用最多的排在前面 前一千个就可以了 数据量太大
	 */
	public static List<WebMashup> getMashupsUseForApi() {
		return getFirstNMashups(1000);
	}

	/**
	 * 获取调用api个数前n的mashups Mashup包最多API的前30个Mashup 这里就可以n=30
	 * 
	 * @param n
	 *            排序前n的数据
	 * @return
	 */
	public static List<WebMashup> getFirstNMashups(int n) {
		WebMashupDao mdao = new WebMashupDaoImpl();
		List<WebMashup> listMashups = mdao.getMashupByKeyWord("");// 获取所有的mashup
		Collections.sort(listMashups, new Comparator<WebMashup>() {

			@Override
			public int compare(WebMashup o1, WebMashup o2) {
				int m1 = 0;
				int m2 = 0;
				if(!"".equals(o1.getMashup_apis().split("\\+"))){
					m1 = o1.getMashup_apis().split("\\+").length;
				}
				if(!"".equals(o2.getMashup_apis().split("\\+"))){
					m2 = o2.getMashup_apis().split("\\+").length;
				}
				if (m1 > m2) {
					return -1;
				} else if (m1 < m2) {
					return 1;
				} else {
					return 0;
				}

			}
		});
		List<WebMashup> newMashups = new ArrayList<WebMashup>();
		for (int i = 0; i < n; i++) {
			newMashups.add(listMashups.get(i));
		}
		return newMashups;
	}

	/**
	 * 计算所有mashup之间的相似度，并写入数据库
	 * 
	 * @param listMashups
	 */
	public static void calcMashupSimilarity(List<WebMashup> listMashups) {
		WebMashup mashup1 = null;
		WebMashup mashup2 = null;
		double score = 0;
		int count = 1;
		long startTime = System.currentTimeMillis();// 开始时间
		WebMashupRelationDao wmrDao = new WebMashupRelationDaoImpl();
		for (int i = 0; i < listMashups.size(); i++) {
			mashup1 = listMashups.get(i);
			for (int j = i; j < listMashups.size(); j++) {
				mashup2 = listMashups.get(j);
				if (i == j) {
					score = 1;
				} else {
					score = Mashup_Mashup_Network.CalculateMashupSimilarity(
							mashup1, mashup2, 0.5, 0.5);// api和tag的权值都为0.5
					// score =
					// Mashup_Mashup_Network.CalculateMashupSimilarity(mashup1,
					// mashup2, 1.0, 0.0);//api权值1和tag的权值0.0
					// score =
					// Mashup_Mashup_Network.CalculateMashupSimilarity(mashup1,
					// mashup2, 0.0, 1.0);//api权值0和tag的权值都为1.0
				}

				if (score > 0.01) {
					// 写文件
					// WriteNetworkScoreFile.writeFile(mashupScorePath,"mashupscore-0.5-0.5.txt",mashup1.getMashup_id()+"#"+mashup2.getMashup_id()+"#"+score);
					// 写入数据库
					wmrDao.addWebMashupRelation(new WebMashupRelation(count, mashup1.getMashup_id(),mashup1.getMashup_name(), mashup2.getMashup_id(),mashup2.getMashup_name(), score + "", "0.5-0.5"));
					System.out.println(mashup1.getMashup_name() + "#"
							+ mashup2.getMashup_name() + "#" + score);
					// 计数
					count++;
				}
			}
		}
		long endTime = System.currentTimeMillis();// 结束时间
		float excTime = (float) (endTime - startTime) / 1000;// 执行时间
		System.out.println("执行时间：" + excTime + "s");
		System.out.println("score>0.1的有" + count + "条数据");
	}

	/**
	 * 计算 List WebMashup 中的mashup的相似度,相似度大于edge的，然后返回List WebMashupRelation
	 * 
	 * @param listMashups
	 * @param a
	 *            api的权值
	 * @param b
	 *            tag的权值
	 * @param edge
	 *            分数范围
	 * @return List WebMashupRelation
	 */
	public static List<WebMashupRelation> calcMashupSimilarity(
			List<WebMashup> listMashups, double a, double b,double edge) {
		WebMashup mashup1 = null;
		WebMashup mashup2 = null;
		List<WebMashupRelation> list = new ArrayList<WebMashupRelation>();
		double score = 0;
		int count = 1;
		for (int i = 0; i < listMashups.size(); i++) {
			mashup1 = listMashups.get(i);
			for (int j = i + 1; j < listMashups.size(); j++) {
				mashup2 = listMashups.get(j);
				if (i == j) {
					score = 1;
				} else {
					score = Mashup_Mashup_Network.CalculateMashupSimilarity(
							mashup1, mashup2, a, b);// api权值a和tag的权值b
				}

				if (score > edge) {
					// 添加到list中
					list.add(new WebMashupRelation(count, mashup1.getMashup_id(),mashup1.getMashup_name(), mashup2.getMashup_id(),mashup2.getMashup_name(), score + "", a + "-" + b));
					// 计数
					count++;
				}
			}
		}
		return list;

	}

}
