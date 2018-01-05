package com.cn.servlet.search;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 徐新凯
 * @date 2017年5月10日 下午7:22:21
 * @description 相关性能指标servlet
 */
@SuppressWarnings("serial")
public class RelatedCharacteristicsIndicatorsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println(type+"......type");
		String apiweight = request.getParameter("apiweight");
		System.out.println(apiweight+"......apiweight");
		String tagweight = request.getParameter("tagweight");
		System.out.println(tagweight+"......tagweight");
		String edge = request.getParameter("edge");
		System.out.println(edge+"......edge");
		
		
		System.out.println("度与度分布：");
		System.out.println("平均最短路径长度：");
		System.out.println("聚集系数：");
		System.out.println("网络密度与集中性：");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
