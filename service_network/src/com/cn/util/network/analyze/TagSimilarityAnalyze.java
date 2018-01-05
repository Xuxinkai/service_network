package com.cn.util.network.analyze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cn.bean.WebApi;
import com.cn.bean.WebMashup;
import com.cn.bean.WebTag;
import com.cn.bean.WebTagRelation;
import com.cn.dao.WebApiDao;
import com.cn.dao.WebMashupDao;
import com.cn.dao.WebTagDao;
import com.cn.dao.WebTagRelationDao;
import com.cn.dao.impl.WebApiDaoImpl;
import com.cn.dao.impl.WebMashupDaoImpl;
import com.cn.dao.impl.WebTagDaoImpl;
import com.cn.dao.impl.WebTagRelationDaoImpl;
import com.cn.util.network.homogeneousNetwork.Tag_Tag_Network;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午8:57:30
 * @description tag的分析：<br>
 *              1.求出标记过api和mashup的tag<br>
 *              2.求出tag之间的相似度,这里有三种情况：a=1,b=0(只考虑api)或a=0,b=1(只考虑mashup)或0《a《1,0
 *              《b《1,a+b=1(api和mashup都考虑);<br>
 *              3.计算tag的总数<br>
 *              4.被API使用的Tag数量、平均使用次数、使用次数分布、使用最多的前30个Tag<br>
 *              5.被Mashup使用的Tag数量、平均使用次数、使用次数分布、使用最多的前30个Tag
 */
public class TagSimilarityAnalyze {
	private static WebTagDao tagDao = new WebTagDaoImpl();

	public static void main(String[] args) {
		addTag();
		calcTagSimilarity(getAllTag());
	}

	/**
	 * 获得tag的总数
	 * 
	 * @return
	 */
	public static int getTagsCount() {
		return tagDao.getAllTags().size();
	}
	/**
	 * 获取所有的tag
	 * @return
	 */
	public static List<WebTag> getAllTag() {
		return tagDao.getAllTags();
	}

	/**
	 * 被API使用的Tag数量
	 * 
	 * @return
	 */
	public static int getTagCountUsedByApi() {
		List<WebTag> listTag = tagDao.getAllTags();
		int count = 0;// 被API使用的Tag数量
		for (WebTag tag : listTag) {
			if (tag.getTag_apis() != "") {
				count++;
			}
		}
		return count;
	}

	/**
	 * 被API使用的Tag的平均使用次数 被调用总次数/被api调用的tag的数量
	 * 
	 * @return
	 */
	public static double getTagUsingAverageByApi() {
		List<WebTag> listTag = tagDao.getAllTags();
		int count = 0;// 被API使用的Tag数量
		int allInvoke = 0;// 被调用总次数
		for (WebTag tag : listTag) {
			if (tag.getTag_apis() != "") {
				allInvoke = allInvoke + tag.getTag_apis().split(",").length;
				count++;
			}
		}
		double average = (double) allInvoke / (double) count;
		return average;
	}

	/**
	 * 被API使用的Tag的使用次数分布 这里我估计是返回一个只有被api使用的tag集合
	 * 
	 * @return
	 */
	public static List<WebTag> getTagUsingFrequencyDistributionByApi() {
		List<WebTag> listTag = tagDao.getAllTags();
		for (int i = 0; i < listTag.size(); i++) {
			if (listTag.get(i).getTag_apis() == "") {
				listTag.remove(i);
			}
		}
		return listTag;
	}

	/**
	 * 被API使用的Tag数量、平均使用次数、使用次数分布、使用最多的前30个Tag 这里我设置n个
	 */
	public static List<WebTag> getFirstNTagsByApi(int n) {

		List<WebTag> listTag = tagDao.getAllTags();
		Collections.sort(listTag, new Comparator<WebTag>() {

			@Override
			public int compare(WebTag o1, WebTag o2) {
				int a1 = 0;
				int a2 = 0;
				if(!"".equals(o1.getTag_apis().split(","))){
					a1 =o1.getTag_apis().split(",").length;
				}
				if(!"".equals(o2.getTag_apis().split(","))){
					a2 =o2.getTag_apis().split(",").length;
				}
				if (a1 > a2) {
					return -1;
				} else if (a1 < a2) {
					return 1;
				} else {
					return 0;
				}

			}
		});
		List<WebTag> newTags = new ArrayList<WebTag>();
		for (int i = 0; i < n; i++) {
			newTags.add(listTag.get(i));
		}
		return newTags;
	}

	/**
	 * 被Mashup使用的Tag数量
	 * 
	 * @return
	 */
	public static int getTagCountUsedByMashup() {
		List<WebTag> listTag = tagDao.getAllTags();
		int count = 0;// 被API使用的Tag数量
		for (WebTag tag : listTag) {
			if (tag.getTag_mashups() != "") {
				count++;
			}
		}
		return count;
	}

	/**
	 * 被Mashup使用的Tag的平均使用次数 被调用总次数/被mashup调用的tag的数量
	 * 
	 * @return
	 */
	public static double getTagUsingAverageByMashup() {
		List<WebTag> listTag = tagDao.getAllTags();
		int count = 0;// 被API使用的Tag数量
		int allInvoke = 0;// 被调用总次数
		for (WebTag tag : listTag) {
			if (tag.getTag_mashups() != "") {
				allInvoke = allInvoke + tag.getTag_mashups().split(",").length;
				count++;
			}
		}
		double average = (double) allInvoke / (double) count;
		return average;
	}

	/**
	 * 被Mashup使用的Tag的使用次数分布 这里我估计是返回一个只有被Mashup使用的tag集合 这里可以显示在html页面的
	 * 
	 * @return
	 */
	public static List<WebTag> getTagUsingFrequencyDistributionByMashup() {
		List<WebTag> listTag = tagDao.getAllTags();
		for (int i = 0; i < listTag.size(); i++) {
			if (listTag.get(i).getTag_mashups() == "") {
				listTag.remove(i);
			}
		}
		return listTag;
	}

	/**
	 *  被Mashup使用的Tag数量、平均使用次数、使用次数分布、使用最多的前30个Tag 这里我设置n个
	 */
	public static List<WebTag> getFirstNTagsByMashup(int n) {
		WebTagDao tagDao = new WebTagDaoImpl();
		List<WebTag> listTag = tagDao.getAllTags();
		Collections.sort(listTag, new Comparator<WebTag>() {

			@Override
			public int compare(WebTag o1, WebTag o2) {

				int m1 = 0;
				int m2 = 0;
				if(!"".equals(o1.getTag_mashups().split(","))){
					m1 =o1.getTag_mashups().split(",").length;
				}
				if(!"".equals(o2.getTag_mashups().split(","))){
					m2 =o2.getTag_mashups().split(",").length;
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
		List<WebTag> newTags = new ArrayList<WebTag>();
		for (int i = 0; i < n; i++) {
			newTags.add(listTag.get(i));
		}
		return newTags;
	}
	/**
	 *  被Mashup和api使用的Tag最多的前30个Tag 这里我设置n个
	 */
	public static List<WebTag> getFirstNTagsByMashupAndApi(int n) {
		WebTagDao tagDao = new WebTagDaoImpl();
		List<WebTag> listTag = tagDao.getAllTags();
		Collections.sort(listTag, new Comparator<WebTag>() {

			@Override
			public int compare(WebTag o1, WebTag o2) {
				int m1 = o1.getTag_mashups().split(",").length+o1.getTag_apis().split(",").length;
				int m2 = o2.getTag_mashups().split(",").length+o2.getTag_apis().split(",").length;
				
				if (m1 > m2) {
					return -1;
				} else if (m1 < m2) {
					return 1;
				} else {
					return 0;
				}

			}
		});
		List<WebTag> newTags = new ArrayList<WebTag>();
		for (int i = 0; i < n; i++) {
			newTags.add(listTag.get(i));
		}
		return newTags;
	}

	/**
	 * 计算tag之间的相似度,并写入数据库
	 */
	public static void calcTagSimilarity(List<WebTag> listTag) {

		// 计算 这里是双重循环
		WebTag tag1 = null;
		WebTag tag2 = null;
		double score = 0;
		double score2 = 0;
		double score3 = 0;
		int count = 1;
		WebTagRelationDao wtrDao = new WebTagRelationDaoImpl();
		long startTime = System.currentTimeMillis();// 开始时间
		for (int i = 0; i < listTag.size(); i++) {
			tag1 = listTag.get(i);
			for (int j = i; j < listTag.size(); j++) {
				tag2 = listTag.get(j);
				if (i == j) {
					score = 1;// 是同一个tag的时候就不用计算了 分数直接为1;
					score2 = 1;
					score3 = 1;
				} else {
					score = Tag_Tag_Network.CalculateTagSimilarity(tag1, tag2,
							0.5, 0.5);// api和mashup的权值都是0.5
					score2 = Tag_Tag_Network.CalculateTagSimilarity(tag1, tag2,
							1.0, 0.0);// api权值是1和mashup的权值是0
					score3 = Tag_Tag_Network.CalculateTagSimilarity(tag1, tag2,
							0.0, 1.0);// api权值是0和mashup的权值是1
				}
				if (score > 0.01) {
					wtrDao.addWebTagRelation(new WebTagRelation(count, tag1
							.getTag_id(), tag2.getTag_id(), score + "",
							"0.5-0.5"));
					System.out.println(tag1.getTag_name() + "#"
							+ tag2.getTag_name() + "#" + score);
					// 计数
					count++;
				}
				if (score2 > 0.01) {
					wtrDao.addWebTagRelation(new WebTagRelation(count, tag1
							.getTag_id(), tag2.getTag_id(), score2 + "", "1-0"));
					System.out.println(tag1.getTag_name() + "#"
							+ tag2.getTag_name() + "#" + score);
					// 计数
					count++;
				}
				if (score3 > 0.01) {
					wtrDao.addWebTagRelation(new WebTagRelation(count, tag1
							.getTag_id(), tag2.getTag_id(), score3 + "", "0-1"));
					System.out.println(tag1.getTag_name() + "#"
							+ tag2.getTag_name() + "#" + score);
					// 计数
					count++;
				}

			}
		}
		long endTime = System.currentTimeMillis();// 结束时间
		float excTime = (float) (endTime - startTime) / 1000;// 执行时间
		System.out.println("执行时间：" + excTime + "s");
		System.out.println("score>0.01的有" + count + "条数据");
	}

	/**
	 * 计算 List WebTag中的tag之间的相似度，返回List WebTagRelation
	 * 
	 * @param listTag
	 * @param a
	 *            api的权值
	 * @param b
	 *            mashup的权值
	 * @param edge 相似度的范围
	 * @return
	 */
	public static List<WebTagRelation> calcTagSimilarity(List<WebTag> listTag,
			double a, double b, double edge) {
		// 计算 这里是双重循环

		WebTag tag1 = null;
		WebTag tag2 = null;
		double score = 0;
		int count = 1;
		List<WebTagRelation> list = new ArrayList<WebTagRelation>();
		for (int i = 0; i < listTag.size(); i++) {
			tag1 = listTag.get(i);
			for (int j = i+1; j < listTag.size(); j++) {
				tag2 = listTag.get(j);
				if (i == j) {
					score = 1;// 是同一个tag的时候就不用计算了 分数直接为1;
				} else {
					score = Tag_Tag_Network.CalculateTagSimilarity(tag1, tag2,
							a, b);// api权值是a 和mashup的权值是b
				}
				if (score > edge) {
					list.add(new WebTagRelation(count, tag1.getTag_id(), tag2
							.getTag_id(), score + "", a+"-"+b));
					// 计数
					count++;
				}
			}
		}
		return list;
	}

	/**
	 * 根据api和mashup两个表来生成一个tag表<br>
	 * tagname(tag的名字) apis(tag标记过的api们) mashups (tag标记过的mashup们)
	 */
	@SuppressWarnings("unused")
	private static void addTag() {
		// 现在两个表中找出所有的tag 然后去重复
		// 首先在api表中寻找tag，在webapi这个表中，tag是以空格" "分割的
		WebApiDao apiDao = new WebApiDaoImpl();
		List<WebApi> listApi = apiDao.getWebApiByKeyWord("");
		// 先获得api中的tag,不考虑重复
		List<String> tags1 = new ArrayList<String>();
		for (WebApi api : listApi) {
			if (api.getApi_tags() != null && api.getApi_tags() != "") {// 当这个api有tag标记时
				String[] apitags = api.getApi_tags().trim().split(" ");
				for (String tag : apitags) {// tags1添加tag
					tags1.add(tag);
				}
			}
		}

		// 在mashup表中寻找tag，在webmashup表中，tag是以逗号","分割的
		WebMashupDao mDao = new WebMashupDaoImpl();
		List<WebMashup> listMashup = mDao.getMashupByKeyWord("");
		// 根据找出来的tag在api和mashup两个表中一一寻找，加入到tag表中
		for (WebMashup m : listMashup) {
			if (m.getMashup_tags() != null && m.getMashup_tags() != "") {// 当这个mashup有tag标记时
				String[] mashuptags = m.getMashup_tags().split(",");
				for (String tag : mashuptags) {// tags1添加tag
					tags1.add(tag);
				}
			}
		}
		// 最后把搜索出来的tags1给去重复,然后赋值给tags，String数组
		String[] tags = removeDuplicates(tags1);

		// 在两个表api和mashup中查询被tag标记过的api和mashup,并存入数据库
		WebTagDao tagDao = new WebTagDaoImpl();
		int i = 0;
		for (String tag : tags) {

			// 获取被此tag标记过的api
			List<WebApi> apis = apiDao.getWebApiByKeyWord("");
			String tag_apis = "";
			for (WebApi api : apis) {
				String tagss = " "+api.getApi_tags().trim()+" ";
				if(tagss.contains(" "+tag+" ")){
					tag_apis = tag_apis + api.getApi_name() + ",";
				}
				
			}
			if(tag_apis != ""){
				tag_apis = tag_apis.substring(0,tag_apis.lastIndexOf(","));
			}
			
			// 获取被次tag标记过的mashup
			List<WebMashup> mashups = mDao.getMashupByKeyWord("");
			String tag_mashups = "";
			for (WebMashup m : mashups) {
				String tagss = ","+m.getMashup_tags()+",";
				if(tagss.contains(tag)){
					tag_mashups = tag_mashups + m.getMashup_name() + ",";
				}
				
			}
			if(tag_mashups != ""){
				tag_mashups = tag_mashups.substring(0,tag_mashups.lastIndexOf(","));
			}
			
			// 存入数据库中
			tagDao.addTag(new WebTag(i, tag, tag_apis, tag_mashups));
			System.out.println(i + "#" + tag + "#" + tag_apis + "#"+ tag_mashups);
			i++;
		}

	}

	/**
	 * 去掉重复的数据
	 * 
	 * @param list
	 * @return
	 */
	private static String[] removeDuplicates(List<String> list) {
		Set<String> hs = new HashSet<String>(list); // 此时已经去掉重复的数据保存在hashset中
		return hs.toArray(new String[] {});
	}
}
