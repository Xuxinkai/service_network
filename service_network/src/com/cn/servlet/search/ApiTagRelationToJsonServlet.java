package com.cn.servlet.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.util.network.heterogeneousNetwork.Api_Tag_Network;
/**
 * 
 * @author 徐新凯
 * @date 2017年5月3日 下午7:39:25
 * @description
 */
@SuppressWarnings("serial")
public class ApiTagRelationToJsonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");// 这条语句指明了向客户端发送的内容格式和采用的字符编码．
		PrintWriter out = response.getWriter();
		String relationJson = "";
		String filePath = this.getServletContext().getRealPath("/search/analyzeData");
		relationJson = new  Api_Tag_Network(filePath).getJson();
		
		out.println(relationJson);// 利用PrintWriter对象的方法将数据发送给客户端 　　
		out.close();
	
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		}

}
