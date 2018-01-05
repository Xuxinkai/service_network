package com.cn.util.network.heterogeneousNetwork;

import java.util.List;

import com.cn.bean.WebApi;
import com.cn.bean.WebTag;
import com.cn.util.DataFileWriteUtil;
import com.cn.util.network.analyze.ApiSimilarityAnalyze;
import com.cn.util.network.analyze.TagSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午1:02:27
 * @description A-T的异构网络 只考虑调用关系，不用考虑边的权值
 */
public class Api_Tag_Network {
	private String filePath;
	public Api_Tag_Network(String filePath) {
		this.filePath = filePath;
	}
	public String getJson(){
		//  然后选被mashup使用的最多的前50个api  最后选被api和mashup使用最多的前50个tag
		List<WebApi> listApi = ApiSimilarityAnalyze.getFirstNApis(100);
		List<WebTag> listTag = TagSimilarityAnalyze.getFirstNTagsByMashupAndApi(100);
		String fileContent = "";
		String fileName = "api-tag.txt";
		//这里就应该生成一个json数据  来展示异构网络图了 这个异构网络图包括 mashup--0,api--1,tag--2  { \"name\": \"MASHUP\",\"value\": 1,\"category\": 2}   一下是模拟数据
		String jsonDataCategories = "{\"name\": \"API\",\"keyword\": {},\"base\": \"API\"},{\"name\": \"TAG\",\"keyword\": {},\"base\": \"TAG\"}";
		
		
		int count = 0;
		String jsonDataNodesApi = "";
		//重新修改id   api的id是从0开始  49结束  这也是它所在的node节点的位置
		for(int i = 0;i < listApi.size();i++){
			
			jsonDataNodesApi = jsonDataNodesApi + "{ \"name\": \""+listApi.get(i).getApi_name()+"\",\"value\": 1,\"category\": 0},";
			
			listApi.get(i).setApi_id(count);
			count++;
		}
		String jsonDataNodesTag = "";
		//重新修改id    tag的id是从50开始  99结束  这也是它所在的node节点的位置
		for(int i = 0;i < listTag.size();i++){
			if(i == listTag.size() - 1){
				jsonDataNodesTag = jsonDataNodesTag + "{ \"name\": \""+listTag.get(i).getTag_name()+"\",\"value\": 1,\"category\": 1}";
			}else{
				jsonDataNodesTag = jsonDataNodesTag + "{ \"name\": \""+listTag.get(i).getTag_name()+"\",\"value\": 1,\"category\": 1},";
			}
			listTag.get(i).setTag_id(count);
			count++;
		}
		String jsonDataNodes = jsonDataNodesApi + jsonDataNodesTag;
		//寻找他们的关系    这个关系很麻烦
		
		String jsonDataLinks = "";
		
		//这是寻找api和tag的关系
		for(int i = 0;i < listApi.size();i++){
			WebApi api = listApi.get(i);
			for(int j = 0;j < listTag.size();j++){
				WebTag tag = listTag.get(j);
				if(api.getApi_tags().contains(tag.getTag_name())){
					jsonDataLinks = jsonDataLinks +"{\"source\": "+api.getApi_id()+",\"target\": "+tag.getTag_id()+" },";
//					System.out.println(api.getApi_id()+" "+tag.getTag_id());
					fileContent = fileContent + api.getApi_id()+" "+tag.getTag_id() + "\r\n";
				}
			}
		}
		jsonDataLinks = jsonDataLinks.substring(0, jsonDataLinks.lastIndexOf(","));
		String jsonData = "{\"type\": \"force\",\"categories\": ["+jsonDataCategories+"],\"nodes\":["+jsonDataNodes+"],\"links\":["+jsonDataLinks+"] }";
		DataFileWriteUtil.write(fileName, filePath, fileContent);
		return jsonData;
	}
	
}
