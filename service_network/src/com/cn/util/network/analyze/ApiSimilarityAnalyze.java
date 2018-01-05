package com.cn.util.network.analyze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cn.bean.WebApi;
import com.cn.bean.WebApiRelation;
import com.cn.bean.WebMashup;
import com.cn.dao.WebApiDao;
import com.cn.dao.WebApiRelationDao;
import com.cn.dao.WebMashupDao;
import com.cn.dao.impl.WebApiDaoImpl;
import com.cn.dao.impl.WebApiRelationDaoImpl;
import com.cn.dao.impl.WebMashupDaoImpl;
import com.cn.util.network.homogeneousNetwork.Api_Api_Network;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月24日 下午9:00:51
 * @description api分析：<br>
 *              1.获得调用api的mashup<br>
 *              2.api相似度分析
 *              这里有三种情况：a=1,b=0(只考虑tag)或a=0,b=1(只考虑mashup)或0《a《1,0《b《1
 *              ,a+b=1(tag和mashup都考虑);<br>
 *              3.api的总数<br>
 *              4.被Mashup使用的API数量、平均使用次数、使用次数分布、使用最多的前30个API<br>
 *              5.API的类别数量以及每个类别拥有的API数量  update
 */
public class ApiSimilarityAnalyze {
	private static String apiScorePath = ApiSimilarityAnalyze.class
			.getResource("/").getPath();
	private static WebApiDao apiDao = new WebApiDaoImpl();

	public static void main(String[] args) {

//		List<WebApi> listApi = getFirstNApis(1000);
//
//		for (int i = 0; i < listApi.size(); i++) {
//			WebApi api = listApi.get(i);
//			System.out.println(i + "::" + api.getApi_name() + "::"
//					+ api.getApi_mashups().split(",").length);
//		}
		Map<String, String> map = getNumberOfApisOwnedByCategory();
		int i= 1;
		for(Entry<String, String> entry : map.entrySet()){
			System.out.println(i+"::"+entry.getKey()+":::"+entry.getValue());
			i++;
		}

		// calcApiSimilarity(listApi);
	}

	/**
	 * 获取api的总数
	 * 
	 * @return
	 */
	public static int getApiCount() {
		return apiDao.getWebApiByKeyWord("").size();
	}
	/**
	 * 获取所有的api
	 * 
	 * @return
	 */
	public static List<WebApi> getAllApis() {
		return apiDao.getWebApiByKeyWord("");
	}

	/**
	 * 被Mashup使用的API数量
	 * 
	 * @return
	 */
	public static int getApiCountUsedByMashup() {
		List<WebApi> listApi = apiDao.getWebApiByKeyWord("");
		int count = 0;
		for (WebApi api : listApi) {
			if (api.getApi_mashups() != "") {
				count++;
			}
		}
		return count;
	}

	/**
	 * 被Mashup使用的api的平均使用次数 被调用总次数/被mashup调用的api的数量
	 * 
	 * @return
	 */
	public static double getApiUsingAverageByMashup() {
		List<WebApi> listApi = apiDao.getWebApiByKeyWord("");
		int count = 0;
		int allInvoke = 0;
		for (WebApi api : listApi) {
			if (api.getApi_mashups() != "") {
				count++;
				allInvoke = allInvoke + api.getApi_mashups().split(",").length;
			}
		}
		double average = (double) allInvoke / (double) count;
		return average;
	}

	/**
	 * 被Mashup使用的api的使用次数分布 这里我估计是返回一个只有被Mashup使用的api集合 这里可以显示在html页面的
	 * 
	 * @return
	 */
	public static List<WebApi> getApiUsedByMashup() {
		// 先获取所有的api
		List<WebApi> listApi = apiDao.getWebApiByKeyWord("");
		for (int i = 0; i < listApi.size(); i++) {
			if (listApi.get(i).getApi_mashups() == "") {
				listApi.remove(i);
			}
		}
		return listApi;
	}

	/**
	 * API的类别(category)数量
	 * 
	 * @return
	 */
	public static int getCategoryCountForApi() {
		// 先获取所有的api
		List<WebApi> listApi = apiDao.getWebApiByKeyWord("");
		List<String> categorys = new ArrayList<String>();
		for (WebApi api : listApi) {
			if(api.getApi_category() != ""){
				categorys.add(api.getApi_category());
			}
			
		}
		String[] cas = removeDuplicates(categorys);
		return cas.length;
	}

	/**
	 * 每个类别(category)拥有的API数量<br>
	 * Map<String, String> String 类别的名字，String api们  以","隔开
	 * @return
	 */
	public static Map<String, String> getNumberOfApisOwnedByCategory(){
		// 先获取所有的api
		List<WebApi> listApi = apiDao.getWebApiByKeyWord("");
		List<String> categorys = new ArrayList<String>();
		for (WebApi api : listApi) {
			if(api.getApi_category() != ""){
				categorys.add(api.getApi_category());
			}
		}
		String[] cas = removeDuplicates(categorys);
		Map<String, String> categoryMap = new HashMap<String, String>();
		for(String s:cas){
			String apis = "";//api的一群名字
			for(WebApi a:listApi){
				if(s.equals(a.getApi_category())){
					apis = apis + a.getApi_name()+",";
				}
			}
			apis = apis.substring(0,apis.lastIndexOf(","));
			categoryMap.put(s, apis);
		}
		
		return sortMapByValue(categoryMap);
	}
	/**
	 * 按值排序(sort by value)<br>
	 * 按值排序就相对麻烦些了，貌似没有直接可用的数据结构能处理类似需求，需要我们自己转换一下。<br>
	 * Map本身按值排序是很有意义的，很多场合下都会遇到类似需求，可以认为其值是定义的某种规则或者权重<br>
	 * @param oriMap
	 * @return
	 */
	private static Map<String, String> sortMapByValue(Map<String, String> oriMap) {  
	    Map<String, String> sortedMap = new LinkedHashMap<String, String>();  
	    if (oriMap != null && !oriMap.isEmpty()) {  
	        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());  
	        Collections.sort(entryList,new Comparator<Map.Entry<String, String>>() {  
	                    public int compare(Entry<String, String> entry1,  
	                            Entry<String, String> entry2) {  
	                        int value1 = 0, value2 = 0;  
	                        try {  
	                            value1 = entry1.getValue().split(",").length;  
	                            value2 = entry2.getValue().split(",").length;  
	                        } catch (NumberFormatException e) {  
	                            value1 = 0;  
	                            value2 = 0;  
	                        }  
	                        return value2 - value1;  
	                    }  
	                });  
	        Iterator<Map.Entry<String, String>> iter = entryList.iterator();  
	        Map.Entry<String, String> tmpEntry = null;  
	        while (iter.hasNext()) {  
	            tmpEntry = iter.next();  
	            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
	        }  
	    }  
	    return sortedMap;  
	}  
	
	/**
	 * 获取被mashup调用次数前n的api<br>
	 * 
	 * 被Mashup使用使用最多的前30个API
	 * 
	 * @param n
	 *            排名前n的数据
	 * @return
	 */
	public static List<WebApi> getFirstNApis(int n) {
		// 先获取所有的api
		List<WebApi> listApi = apiDao.getWebApiByKeyWord("");
		Collections.sort(listApi, new Comparator<WebApi>() {

			@Override
			public int compare(WebApi o1, WebApi o2) {
				int a1 = 0;
				int a2 = 0;
				if(!"".equals(o1.getApi_mashups().split(","))){
					a1 =o1.getApi_mashups().split(",").length;
				}
				if(!"".equals(o2.getApi_mashups().split(","))){
					a2 =o2.getApi_mashups().split(",").length;
				}
				int result = 0;
				if (a1 > a2) {
					result = -1;
				} else if (a1 < a2) {
					result = 1;
				}
				return result;
			}
		});
		List<WebApi> newApis = new ArrayList<WebApi>();
		for (int i = 0; i < n; i++) {
			newApis.add(listApi.get(i));
		}
		return newApis;
	}

	/**
	 * 计算API相似度,并写入数据库
	 */
	public static void calcApiSimilarity(List<WebApi> listApi) {
		// 又是两重循环 很费内存和时间的
		WebApi api1 = null;
		WebApi api2 = null;
		double score = 0;
		int count = 1;
		WebApiRelationDao warDao = new WebApiRelationDaoImpl();
		long startTime = System.currentTimeMillis();// 开始时间
		for (int i = 0; i < listApi.size(); i++) {
			api1 = listApi.get(i);
			for (int j = i; j < listApi.size(); j++) {
				api2 = listApi.get(j);
				if (i == j) {
					score = 1;// 是同一个api的时候就不用计算了 分数直接为1;
				} else {
					score = Api_Api_Network.CalculateAPISimilarity(api1, api2,
							0.5, 0.5);// tag权值为0.5 mashup的权值为0.5
					// score = Api_Api_Network.CalculateAPISimilarity(api1,
					// api2, 1, 0);//tag权值为1 mashup的权值为0
					// score = Api_Api_Network.CalculateAPISimilarity(api1,
					// api2, 0, 1);//tag权值为0 mashup的权值为1
				}
				if (score > 0.01) {
					System.out.println(api1.getApi_name() + "#"
							+ api2.getApi_name() + "#" + score);
					warDao.addWebApiRealtion(new WebApiRelation(count, api1
							.getApi_id(), api2.getApi_id(), score + "",
							"0.5-0.5"));
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
	 * 计算List WebApi中的api相似度，并返回List WebApiRelation
	 * 
	 * @param listApi
	 * @param a
	 *            tag的权值
	 * @param b
	 *            mashup的权值
	 * @param edge 相似度的范围
	 * @return List WebApiRelation
	 */
	public static List<WebApiRelation> calcApiSimilarity(List<WebApi> listApi, double a, double b,double edge) {
		// 又是两重循环 很费内存和时间的
		WebApi api1 = null;
		WebApi api2 = null;
		double score = 0;
		int count = 1;
		List<WebApiRelation> list = new ArrayList<WebApiRelation>();
		for (int i = 0; i < listApi.size(); i++) {
			api1 = listApi.get(i);
			for (int j = i + 1; j < listApi.size(); j++) {
				api2 = listApi.get(j);
				if (i == j) {
					score = 1;// 是同一个api的时候就不用计算了 分数直接为1;
				} else {
					score = Api_Api_Network.CalculateAPISimilarity(api1, api2,
							a, b);// tag权值为a mashup的权值为b

				}
				if (score > edge) {
					list.add(new WebApiRelation(count, api1.getApi_id(), api2
							.getApi_id(), score + "", a + "-" + b));
					// 计数
					count++;
				}
			}
		}
		return list;
	}
	/**
	 * 计算给定的WebApi 与 List WebApi中的api相似度，并返回List WebApiRelation  评分排名前N个api
	 * 
	 * @param listApi
	 * @param api  给定的api
	 * @param a
	 *            tag的权值
	 * @param b
	 *            mashup的权值
	 *@param n  评分前n名的api
	 * @return List WebApiRelation
	 */
	public static List<WebApiRelation> calcApiSimilarity(List<WebApi> listApi, WebApi api, double a, double b,int n) {
		
		WebApi api1 = null;
		double score = 0;
		int count = 1;
		List<WebApiRelation> list = new ArrayList<WebApiRelation>();
		for (int i = 0; i < listApi.size(); i++) {
			api1 = listApi.get(i);
			if(api1.getApi_name().equals(api.getApi_name())){
				score = 1;
			}else{
				score = Api_Api_Network.CalculateAPISimilarity(api, api1, a, b);// tag权值为a mashup的权值为b
			}

			if (score > 0.1) {
				list.add(new WebApiRelation(count, api.getApi_id(), api.getApi_name(), api1.getApi_id(), api1.getApi_name(), score + "", a + "-" + b));
				// 计数
				count++;
			}
		}
		Collections.sort(list, new Comparator<WebApiRelation>() {

			@Override
			public int compare(WebApiRelation o1, WebApiRelation o2) {
				double weight1 = Double.parseDouble(o1.getWeight());
				double weight2 = Double.parseDouble(o2.getWeight());
				if(weight1 > weight2){
					return -1;
				}else if(weight1 < weight2){
					return 1;
				}else{
					return 0;
				}
				
			}
		});
		
		List<WebApiRelation> newWebApiRelations = new ArrayList<WebApiRelation>();
		if(n>=list.size()){
			n=list.size();
		}
		for (int i = 0; i < n; i++) {
			newWebApiRelations.add(list.get(i));
		}
		return newWebApiRelations;
	}

	/**
	 * 获得调用api的mashup ，然后存入数据库
	 */
	@SuppressWarnings("unused")
	private static void getApiMashups(WebApi api) {
		// 首先获得api的名字 数据库中 mashup的表的api字段是没有带API字的， 这里要处理一下
		String apiName = api.getApi_name().replace(" API", "");
		// 根据关键字查找调用过此api的mashup 先不考虑是否重复
		WebMashupDao mDao = new WebMashupDaoImpl();
		List<WebMashup> listMashup = mDao.getMashupByApiName(apiName);
		String mashups = "";
		for (WebMashup m : listMashup) {
			mashups = mashups + m.getMashup_name() + ",";
		}
		// WriteNetworkScoreFile.writeFile(apiScorePath,
		// "api-mashups.txt",apiName+" API # "+mashups);
		apiDao.updateMashupsByApiId(mashups, api.getApi_id());
		System.out.println(apiName + " API # " + mashups);
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
