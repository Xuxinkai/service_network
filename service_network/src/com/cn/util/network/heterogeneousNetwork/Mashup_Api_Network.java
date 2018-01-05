package com.cn.util.network.heterogeneousNetwork;

import java.util.List;

import com.cn.bean.WebApi;
import com.cn.bean.WebMashup;
import com.cn.util.DataFileWriteUtil;
import com.cn.util.network.analyze.ApiSimilarityAnalyze;
import com.cn.util.network.analyze.MashupSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午1:02:27
 * @description M-A的异构网络 只考虑调用关系，不用考虑边的权值
 */
public class Mashup_Api_Network {
	private String filePath;
	public Mashup_Api_Network(String filePath){
		this.filePath = filePath;
	}
	public String getJson() {
		// 先选使用api最多的前50个mashup 然后选被mashup使用的最多的前50个api
		List<WebMashup> listMashup = MashupSimilarityAnalyze
				.getFirstNMashups(100);
		List<WebApi> listApi = ApiSimilarityAnalyze.getFirstNApis(100);
		String fileContent = "";
		String fileName = "mashup-api.txt";
		// 这里就应该生成一个json数据 来展示异构网络图了 这个异构网络图包括 mashup--0,api--1  {\"name\": \"MASHUP\",\"value\": 1,\"category\": 2} 一下是模拟数据
		String jsonDataCategories = "{\"name\": \"MASHUP\",\"keyword\": {},\"base\": \"MASHUP\"},{\"name\": \"API\",\"keyword\": {},\"base\": \"API\"}";
		String jsonDataNodesMashup = "";
		// 重新修改id mashup的id是从0开始 49结束 这也是它所在的node节点的位置
		int count = 0;
		for (int i = 0; i < listMashup.size(); i++) {

			jsonDataNodesMashup = jsonDataNodesMashup + "{ \"name\": \""
					+ listMashup.get(i).getMashup_name()
					+ "\",\"value\": 1,\"category\": 0},";

			listMashup.get(i).setMashup_id(count);
			count++;
		}
		String jsonDataNodesApi = "";
		// 重新修改id api的id是从50开始 99结束 这也是它所在的node节点的位置
		for (int i = 0; i < listApi.size(); i++) {
			if (i == listApi.size() - 1) {
				jsonDataNodesApi = jsonDataNodesApi + "{ \"name\": \""
						+ listApi.get(i).getApi_name()
						+ "\",\"value\": 1,\"category\": 1}";
			} else {
				jsonDataNodesApi = jsonDataNodesApi + "{ \"name\": \""
						+ listApi.get(i).getApi_name()
						+ "\",\"value\": 1,\"category\": 1},";
			}

			listApi.get(i).setApi_id(count);
			count++;
		}
		String jsonDataNodes = jsonDataNodesMashup + jsonDataNodesApi;
		// 寻找他们的关系 这个关系很麻烦
		// 先找mashup和api的调用关系
		String jsonDataLinks = "";
		for (int i = 0; i < listMashup.size(); i++) {
			WebMashup mashup = listMashup.get(i);
			for (int j = 0; j < listApi.size(); j++) {
				WebApi api = listApi.get(j);
				if (mashup.getMashup_apis().contains(
						api.getApi_name().replace(" API", ""))) {
					jsonDataLinks = jsonDataLinks + "{\"source\": "
							+ mashup.getMashup_id() + ",\"target\": "
							+ api.getApi_id() + " },";
//					System.out.println(mashup.getMashup_id()+" "+api.getApi_id());
					fileContent = fileContent + mashup.getMashup_id()+" "+api.getApi_id()+"\r\n";
				}
			}

		}
		
		jsonDataLinks = jsonDataLinks.substring(0,
				jsonDataLinks.lastIndexOf(","));
		String jsonData = "{\"type\": \"force\",\"categories\": ["
				+ jsonDataCategories + "],\"nodes\":[" + jsonDataNodes
				+ "],\"links\":[" + jsonDataLinks + "] }";
		DataFileWriteUtil.write(fileName, filePath, fileContent);
		return jsonData;
	}

}
