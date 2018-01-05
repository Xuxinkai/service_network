package com.cn.servlet.search.statistics;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.bean.WebTag;
import com.cn.util.network.analyze.TagSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年5月4日 下午6:38:04
 * @description  第六个统计的servlet 传输数据的<br>
 *              被Mashup使用的Tag数量、平均使用次数、使用次数分布、使用最多的前30个Tag
 */
@SuppressWarnings("serial")
public class Statistics6Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取被Mashup使用的Tag数量
		int tagCountUsedByMashup = TagSimilarityAnalyze.getTagCountUsedByMashup();
		// 获取被Mashup使用的Tag平均使用次数
		double averageUseofAPI = TagSimilarityAnalyze.getTagUsingAverageByMashup();
		String averageUseofAPIresult = String.format("%.2f", averageUseofAPI);
		/************ 接下来是被api使用的tag使用次数分布 *******/
		// 首先获取所有的tag
		List<WebTag> listTag = TagSimilarityAnalyze.getAllTag();
		int zero = 0;// api个数等于0的
		int one = 0;// api个数等于1的
		int twoFive = 0;// 2到5之间的
		int sixFifteen = 0;// 6到15之间的
		int sixteenNinetyNine = 0;// 16到99之间
		int upOneHundred = 0;// 大于100个的
		for (WebTag t : listTag) {
			int count = t.getTag_mashups().split(",").length;
			if ("".equals(t.getTag_mashups())) {
				zero++;
			}
			if (count == 1 && !"".equals(t.getTag_mashups())) {
				one++;
			}
			if (count >= 2 && count <= 5) {
				twoFive++;
			}
			if (count >= 6 && count <= 15) {
				sixFifteen++;
			}
			if (count >= 16 && count <= 99) {
				sixteenNinetyNine++;
			}
			if (count >= 100) {
				upOneHundred++;
			}
		}
		/*********** 被mashup使用最多的前30个tag *************/
		List<WebTag> listTagUsedByApi = TagSimilarityAnalyze.getFirstNTagsByMashup(30);
		String xAxisData = "";// x轴的数据
		String seriesData = "";// 要显示的数据
		for (WebTag t : listTagUsedByApi) {
			xAxisData = xAxisData + "\"" + t.getTag_name() + "\",";
			seriesData = seriesData + t.getTag_mashups().split(",").length + ",";
		}

		xAxisData = xAxisData.substring(0, xAxisData.lastIndexOf(","));
		seriesData = seriesData.substring(0, seriesData.lastIndexOf(","));

		request.setAttribute("zero", zero);
		request.setAttribute("one", one);
		request.setAttribute("twoFive", twoFive);
		request.setAttribute("sixFifteen", sixFifteen);
		request.setAttribute("sixteenNinetyNine", sixteenNinetyNine);
		request.setAttribute("upOneHundred", upOneHundred);
		request.setAttribute("xAxisData", xAxisData);
		request.setAttribute("seriesData", seriesData);
		request.setAttribute("tagCountUsedByMashup", tagCountUsedByMashup);
		request.setAttribute("averageUseofAPI", averageUseofAPIresult);
		request.getRequestDispatcher("statistics6.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
