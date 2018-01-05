package com.cn.servlet.search.statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 徐新凯
 * @date 2017年5月4日 下午5:38:16
 * @description 第四个统计的servlet 传输数据的<br>
 *              API的类别数量以及每个类别拥有的API数量
 */
@SuppressWarnings("serial")
public class Statistics4Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("statistics4.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
