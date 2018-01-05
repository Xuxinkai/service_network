package com.cn.servlet.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.bean.WebApi;
import com.cn.bean.WebApiRelation;
import com.cn.dao.WebApiDao;
import com.cn.dao.impl.WebApiDaoImpl;
import com.cn.util.network.analyze.ApiSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月27日 下午11:31:00
 * @description 获取api的信息 这里包括该api的所有信息，与之有关联的tag mashup 和相似度大的api
 */
@SuppressWarnings("serial")
public class ApiInfomationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String api_id = request.getParameter("api_id");

		
		// 获取api的javabean
		WebApiDao apiDao = new WebApiDaoImpl();
		WebApi api = apiDao.getWebApiById(Integer.parseInt(api_id));
		
		//获取相关的api信息
		List<WebApiRelation> listwar = new ArrayList<WebApiRelation>();
		listwar = ApiSimilarityAnalyze.calcApiSimilarity(apiDao.getWebApiByKeyWord(""), api, 0.5, 0.5,11);//获取评分  也就是相似度分数前11名的api们
		//这里就应该生成一个json数据  来展示异构网络图了 这个异构网络图包括 api--0,tag--1,mashup--2
		String jsonDataCategories = "{\"name\": \"API\",\"keyword\": {},\"base\": \"API\"},{\"name\": \"TAG\",\"keyword\": {},\"base\": \"TAG\"},{\"name\": \"MASHUP\",\"keyword\": {},\"base\": \"MASHUP\"}";
		
		
		// "{ \"name\": \"API1\",\"value\": 1,\"category\": 0},{ \"name\": \"API2\",\"value\": 1,\"category\": 0}";//"{ \"name\": \"API1\",\"value\": 1,\"category\": 0},{ \"name\": \"API2\",\"value\": 1,\"category\": 0},{ \"name\": \"API3\",\"value\": 1,\"category\": 0},{ \"name\": \"TAG3\",\"value\": 1,\"category\": 1},{ \"name\": \"TAG2\",\"value\": 1,\"category\": 1},{ \"name\": \"MASHUP\",\"value\": 1,\"category\": 2}";
		String jsonDataNodesApi = "";
		for(int i = 0;i < listwar.size();i++){
			if(i == listwar.size() - 1){//最后一个不需要","号
				jsonDataNodesApi = jsonDataNodesApi +"{ \"name\": \""+listwar.get(i).getApi_two_name()+"\",\"value\": 1,\"category\": 0}";
			}else{
				jsonDataNodesApi = jsonDataNodesApi +"{ \"name\": \""+listwar.get(i).getApi_two_name()+"\",\"value\": 1,\"category\": 0},";
			}
			
		}
		String jsonDataNodesTag = "";
		String[] tags = api.getApi_tags().split(" ");
		int i = 0;
		for(String tag:tags){
			if(i == tags.length-1){
				jsonDataNodesTag = jsonDataNodesTag + "{ \"name\": \""+tag+"\",\"value\": 1,\"category\": 1}";
			}else{
				jsonDataNodesTag = jsonDataNodesTag + "{ \"name\": \""+tag+"\",\"value\": 1,\"category\": 1},";
			}
			i++;
		}
		String jsonDataNodesMashup = "";
		String[] mashups = api.getApi_mashups().split(",");
		int j = 0;
		for(String mashup:mashups){
			if(j == mashups.length - 1){
				jsonDataNodesMashup = jsonDataNodesMashup + "{ \"name\": \""+mashup+"\",\"value\": 1,\"category\": 2}";
			}else{
				jsonDataNodesMashup = jsonDataNodesMashup + "{ \"name\": \""+mashup+"\",\"value\": 1,\"category\": 2},";
			}
			j++;
		}
		String jsonDataNodes = jsonDataNodesApi +","+ jsonDataNodesTag +","+ jsonDataNodesMashup;
		int count = listwar.size() + tags.length + mashups.length;
		String jsonDataLinks = "";
		for(int k = 0;k < count;k++){
			if(k == count - 1){
				jsonDataLinks = jsonDataLinks +"{\"source\": 0,\"target\": "+(k)+" }";
			}else{
				jsonDataLinks = jsonDataLinks +"{\"source\": 0,\"target\": "+(k)+" },";
			}
		}
		//jsonDataLinks = "{\"source\": 0,\"target\": 1 },{\"source\": 0,\"target\": 2 },{\"source\": 0,\"target\": 3},{\"source\": 0,\"target\": 4},{\"source\": 0,\"target\": 5}";
		
		
		
		String jsonData = "{\"type\": \"force\",\"categories\": ["+jsonDataCategories+"],\"nodes\":["+jsonDataNodes+"],\"links\":["+jsonDataLinks+"] }";
		//System.out.println(jsonData);
		//转发，存信息 总数
		request.setAttribute("tagSum", tags.length);
		request.setAttribute("mashupSum", mashups.length);
		request.setAttribute("api", api);
		request.setAttribute("jsonData", jsonData);
		request.setAttribute("listwar", listwar);
		request.getRequestDispatcher("single.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
