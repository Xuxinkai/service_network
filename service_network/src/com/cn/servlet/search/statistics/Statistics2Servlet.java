package com.cn.servlet.search.statistics;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cn.bean.WebMashup;
import com.cn.util.network.analyze.MashupSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年5月4日 下午2:01:20
 * @description 第二个统计的servlet 传输数据的<br>
 * 2.Mashup包含的API数量分布及包含最多API的前30个Mashup
 */
@SuppressWarnings("serial")
public class Statistics2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先获取所有的mashup
		List<WebMashup> listMashup = MashupSimilarityAnalyze.getAllMashups();
		int zero = 0;//api个数等于0的
		int one = 0;//api个数等于1的
		int twoEight = 0;//2到8之间的 
		int NineFifteen = 0;//9到15之间的
		int upSixteen = 0;//大于16个的
		for(WebMashup m:listMashup){
			int apiCount = m.getMashup_apis().split("\\+").length;
			if("".equals(m.getMashup_apis())){
				zero++;
			}
			if(apiCount == 1 && !"".equals(m.getMashup_apis())){
				one++;
			}
			if(2<=apiCount && apiCount <=8){
				twoEight++;
			}
			if(9<=apiCount && apiCount <=15){
				NineFifteen++;
			}
			if(16<=apiCount){
				upSixteen++;
			}
		}
		
		//获取包含最多API的前30个Mashup
		List<WebMashup> listMashupByApi = MashupSimilarityAnalyze.getFirstNMashups(30);
		String xAxisData = "";//x轴的数据
		String seriesData = "";//要显示的数据
		for(WebMashup m:listMashupByApi){
			xAxisData = xAxisData + "\""+m.getMashup_name()+"\",";
			seriesData = seriesData + m.getMashup_apis().split("\\+").length+",";
		}
		xAxisData = xAxisData.substring(0, xAxisData.lastIndexOf(","));
		seriesData = seriesData.substring(0, seriesData.lastIndexOf(","));
		request.setAttribute("zero", zero);
		request.setAttribute("one", one);
		request.setAttribute("twoEight", twoEight);
		request.setAttribute("NineFifteen", NineFifteen);
		request.setAttribute("upSixteen", upSixteen);
		request.setAttribute("xAxisData", xAxisData);
		request.setAttribute("seriesData", seriesData);
		request.getRequestDispatcher("statistics2.jsp").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	

}
