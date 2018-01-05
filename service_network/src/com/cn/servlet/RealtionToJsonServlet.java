package com.cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.util.RelationToJsonUtil;

@SuppressWarnings("serial")
public class RealtionToJsonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");// 这条语句指明了向客户端发送的内容格式和采用的字符编码．
															// 　　

		PrintWriter out = response.getWriter();

		String relationJson = "";
		String _weight = request.getParameter("_weight");// 获取传过来的边的weight
		String _relationType = request.getParameter("_relationType");
		// System.out.println("+++++"+_weight);
		if (_weight == "" || _weight == null) {
			_weight = "0.1";// 默认值0.1
		}
		if (_relationType == "" || _relationType == null) {
			_relationType = "api";// 默认值api
		}

		relationJson = RelationToJsonUtil.getJson(_relationType, _weight);

		out.println(relationJson);// 利用PrintWriter对象的方法将数据发送给客户端 　　
		out.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
