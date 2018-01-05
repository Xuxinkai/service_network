package com.cn.servlet.search.statistics;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.cn.bean.WebApi;
import com.cn.util.network.analyze.ApiSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年5月4日 下午3:04:52
 * @description 第三个统计的servlet 传输数据的<br>
 *  被Mashup使用的API数量、平均使用次数、使用次数分布、使用最多的前30个API
 */
@SuppressWarnings("serial")
public class Statistics3Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		List<WebApi> listApi = ApiSimilarityAnalyze.get
		//获取被mashup调用过的api的数量
		int apiCountUsedByMashup = ApiSimilarityAnalyze.getApiCountUsedByMashup();
		//api的平均使用次数  使用总次数/被使用的apide数量（apiCountUsedByMashup）
		double averageUseofAPI = ApiSimilarityAnalyze.getApiUsingAverageByMashup();
		String averageUseofAPIresult = String.format("%.2f", averageUseofAPI);
		/************接下来是被Mashup使用的API使用次数分布*******/
		//先获取所有的api
		List<WebApi> listApi = ApiSimilarityAnalyze.getAllApis();
		int zero = 0;//api个数等于0的
		int one = 0;//api个数等于1的
		int twoFive = 0;//2到5之间的 
		int sixFifteen = 0;//6到15之间的
		int sixteenNinetyNine = 0;//16到99之间
		int upOneHundred = 0;//大于100个的
		for(WebApi a:listApi){
			int count = a.getApi_mashups().split(",").length;
			if("".equals(a.getApi_mashups())){
				zero++;
			}
			if(count == 1 && !"".equals(a.getApi_mashups())){
				one++;
			}
			if(count >= 2 && count <= 5){
				twoFive++;
			}
			if(count >= 6 && count <= 15){
				sixFifteen++;
			}
			if(count >= 16 && count <= 99){
				sixteenNinetyNine++;
			}
			if(count >= 100){
				upOneHundred++;
			}
		}
		/***********被Mashup使用最多的前30个API*************/
		List<WebApi> listApiUsedByMashup = ApiSimilarityAnalyze.getFirstNApis(30);
		String xAxisData = "";//x轴的数据
		String seriesData = "";//要显示的数据
		for(WebApi a:listApiUsedByMashup){
			xAxisData = xAxisData + "\""+a.getApi_name()+"\",";
			seriesData = seriesData + a.getApi_mashups().split(",").length+",";
		}
		xAxisData = xAxisData.substring(0, xAxisData.lastIndexOf(","));
		seriesData = seriesData.substring(0, seriesData.lastIndexOf(","));
		
		
		request.setAttribute("apiCountUsedByMashup", apiCountUsedByMashup);
		request.setAttribute("averageUseofAPI", averageUseofAPIresult);
		request.setAttribute("zero", zero);
		request.setAttribute("one", one);
		request.setAttribute("twoFive", twoFive);
		request.setAttribute("sixFifteen", sixFifteen);
		request.setAttribute("sixteenNinetyNine", sixteenNinetyNine);
		request.setAttribute("upOneHundred", upOneHundred);
		request.setAttribute("xAxisData", xAxisData);
		request.setAttribute("seriesData", seriesData);
		request.getRequestDispatcher("statistics3.jsp").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	@Test
	public void ddd(){
		List<WebApi> listApi = ApiSimilarityAnalyze.getFirstNApis(1000);
		for(WebApi a:listApi){
			System.out.println(a.getApi_name()+"::"+a.getApi_mashups().split(",").length);
		}
		
	}
}
