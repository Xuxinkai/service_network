package com.cn.servlet.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author 徐新凯
 * @date 2017年5月26日 上午11:21:25
 * @description 下载图要分析的数据   以txt的形式下载
 */
public class AnalyzeDataDownLoadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		down(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
	/**
	 * 下载动作
	 * @param request
	 * @param response
	 */
	public void down(HttpServletRequest request, HttpServletResponse response){	
		
		String fileName = request.getParameter("fileName");//获取文件名
		try {
			fileName = new String(fileName.getBytes("iso8859-1"),"utf-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//得到根目录
		String bootPath = this.getServletContext().getRealPath("/search/analyzeData");
		
		//得到文件
		File file = new File(bootPath , fileName);
		
		// 如果文件名是中文，需要进行url编码
	    try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			//得到文件流
			FileInputStream fis = new FileInputStream(file);
			
			//设置文件的响应头
			response.setHeader("content-disposition", "attachment;fileName=" + fileName);
			
			//得到输出流
			OutputStream os = response.getOutputStream();
			
			//边读边写
			//缓冲数组
			byte[] buff = new byte[1024];
			int len = 0 ;
			while((len = fis.read(buff)) != -1){
				os.write(buff, 0, len);
			}
			//关闭流
			os.close();
			fis.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
