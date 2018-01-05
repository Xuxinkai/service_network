package com.cn.util.network.heterogeneousNetwork;

import java.util.List;

import com.cn.bean.WebApi;
import com.cn.bean.WebMashup;
import com.cn.bean.WebTag;
import com.cn.util.DataFileWriteUtil;
import com.cn.util.network.analyze.ApiSimilarityAnalyze;
import com.cn.util.network.analyze.MashupSimilarityAnalyze;
import com.cn.util.network.analyze.TagSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午1:02:27
 * @description M-T的异构网络 只考虑调用关系，不用考虑边的权值
 */
public class Mashup_Tag_Network {
	private String filePath;
	public Mashup_Tag_Network(String filePath) {
		this.filePath = filePath;
	}
	public String getJson(){
		//先选使用api最多的前50个mashup  然后选被mashup使用的最多的前50个api  最后选被api和mashup使用最多的前50个tag
				List<WebMashup> listMashup = MashupSimilarityAnalyze.getFirstNMashups(100);
				List<WebTag> listTag = TagSimilarityAnalyze.getFirstNTagsByMashupAndApi(100);
				String fileContent = "";
				String fileName = "mashup-tag.txt";
				//这里就应该生成一个json数据  来展示异构网络图了 这个异构网络图包括 mashup--0,tag--1  { \"name\": \"MASHUP\",\"value\": 1,\"category\": 2}   一下是模拟数据
				String jsonDataCategories = "{\"name\": \"MASHUP\",\"keyword\": {},\"base\": \"MASHUP\"},{\"name\": \"TAG\",\"keyword\": {},\"base\": \"TAG\"}";
				String jsonDataNodesMashup = "";
				//重新修改id   mashup的id是从0开始  49结束   这也是它所在的node节点的位置
				int count = 0;
				for(int i = 0;i < listMashup.size();i++){
					
					jsonDataNodesMashup = jsonDataNodesMashup + "{ \"name\": \""+listMashup.get(i).getMashup_name()+"\",\"value\": 1,\"category\": 0},";
					
					listMashup.get(i).setMashup_id(count);
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
				String jsonDataNodes =  jsonDataNodesMashup + jsonDataNodesTag;
				//寻找他们的关系    这个关系很麻烦
				String jsonDataLinks = "";
				//这是寻找mashup和tag的关系
				for(int i = 0;i < listMashup.size();i++){
					WebMashup mashup = listMashup.get(i);
					for(int j = 0;j < listTag.size();j++){
						WebTag tag = listTag.get(j);
						if(mashup.getMashup_apis().contains(tag.getTag_name())){
							jsonDataLinks = jsonDataLinks +"{\"source\": "+mashup.getMashup_id()+",\"target\": "+tag.getTag_id()+" },";
							//System.out.println(mashup.getMashup_id()+" "+tag.getTag_id());
							fileContent = fileContent + mashup.getMashup_id()+" "+tag.getTag_id()+"\r\n";
						}
					}
					
				}
				
				jsonDataLinks = jsonDataLinks.substring(0, jsonDataLinks.lastIndexOf(","));
				String jsonData = "{\"type\": \"force\",\"categories\": ["+jsonDataCategories+"],\"nodes\":["+jsonDataNodes+"],\"links\":["+jsonDataLinks+"] }";
				DataFileWriteUtil.write(fileName, filePath, fileContent);
				return jsonData;
	}

}
